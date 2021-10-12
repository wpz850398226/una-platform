package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.mapper.CpGoodsMapper;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpModel;
import cn.kunli.una.pojo.chanpin.CpSpecification;
import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.system.SysRegionService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 账号(CpGoods)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Service
public class CpGoodsService extends BasicService<CpGoodsMapper, CpGoods> {
    @Autowired
    private CpGoodsService thisProxy;
    @Autowired
    private CpSpecificationService cpSpecificationService;
    @Autowired
    private CpModelService cpModelService;

    @Autowired
    private SysRegionService sysRegionService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public SysResult validate(CpGoods obj) {
        SysResult validate = super.validate(obj);
        if(!validate.getIsSuccess())return validate;

        if(obj.getId()==null){
            if(CollectionUtils.isEmpty(obj.getSpecificationList())){
                return SysResult.fail("保存失败，规格不能为空");
            }
            if(CollectionUtils.isEmpty(obj.getModelList())){
                return SysResult.fail("保存失败，型号不能为空");
            }
        }

        return SysResult.success();
    }

    @Override
    public CpGoods initialize(CpGoods obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            obj.setCode(UUID.randomUUID().toString().replace("-",""));
            SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
            if(loginUser.getCompanyId()!=null){
                obj.setCompanyId(loginUser.getCompanyId());
                SysCompany sysCompany = sysCompanyService.getById(loginUser.getCompanyId());
                if(StringUtils.isBlank(obj.getRegionIds())){
                    obj.setRegionIds(sysCompany.getRegionIds());
                }
                if(StringUtils.isBlank(obj.getIndustryTypeDcodes())){
                    obj.setIndustryTypeDcodes(sysCompany.getIndustryTypeDcodes());
                }
            }

        }else{
            if(obj.getIsAudit()==null)obj.setIsAudit(false);    //如果修改，默认改为未审核
        }

        return obj;
    }

    @Override
    public SysResult afterSaveSuccess(CpGoods obj) {
        if(CollectionUtils.isNotEmpty(obj.getSpecificationList())){
            //删除原有规格
            cpSpecificationService.deleteBySelective(MapUtil.getMap("goodsId", obj.getId()));
            //保存规格
            for (CpSpecification cpSpecification : obj.getSpecificationList()) {
                cpSpecification.setGoodsId(obj.getId());
                cpSpecificationService.saveRecord(cpSpecification);
            }
        }

        if(CollectionUtils.isNotEmpty(obj.getModelList())){
            //删除原有型号
            cpModelService.deleteBySelective(MapUtil.getMap("goodsId", obj.getId()));

            CpGoods targetGoods = (CpGoods) new CpGoods().setIsAudit(obj.getIsAudit()).setId(obj.getId());
            //取标价最低的型号作为展示型号
            CpModel lowestModel = obj.getModelList().get(0);
            //保存型号
            for (CpModel cpModel : obj.getModelList()) {
                cpModel.setGoodsId(obj.getId());
                cpModelService.saveRecord(cpModel);
                //取最低标价
                if(cpModel.getSellingPrice()<=lowestModel.getSellingPrice()){
                    lowestModel = cpModel;
                }
            }

            targetGoods.setSellingPrice(lowestModel.getSellingPrice())
                    .setCeilingPrice(lowestModel.getCeilingPrice()).setCostPrice(lowestModel.getCostPrice())
                    .setFloorPrice(lowestModel.getFloorPrice()).setWholesalePrice(lowestModel.getWholesalePrice())
                    .setTaxExclusiveMarketPrice(lowestModel.getTaxExclusiveMarketPrice())
                    .setTaxInclusiveMarketPrice(lowestModel.getTaxInclusiveMarketPrice());

            //修改商品
            SysResult sysResult = thisProxy.updateRecordById(targetGoods);
            if(!sysResult.getIsSuccess())return sysResult;
        }
        return SysResult.success();
    }

    @Override
    public List<CpGoods> parse(List<CpGoods> list) {
        list = super.parse(list);
        for (CpGoods cpGoods : list) {
            List<CpSpecification> specificationList = cpSpecificationService.selectList(MapUtil.getMap("goodsId", cpGoods.getId()));
            cpGoods.setSpecificationList(specificationList);
            List<CpModel> attributeList = cpModelService.selectList(MapUtil.getMap("goodsId", cpGoods.getId()));
            cpGoods.setModelList(attributeList);
            if(StringUtils.isNotBlank(cpGoods.getFileIds())){
                String fileUrls = String.valueOf(cpGoods.getMap().get("fileUrls"));
                if(fileUrls.contains(",")){
                    cpGoods.setTitleImgUrl(fileUrls.substring(0,fileUrls.indexOf(",")));
                }else{
                    cpGoods.setTitleImgUrl(fileUrls);
                }

            }
            SysCompany sysCompany = sysCompanyService.getById(cpGoods.getCompanyId());
            cpGoods.setIsOpenShop(sysCompany.getIsFacade());
        }
        return list;
    }
}
