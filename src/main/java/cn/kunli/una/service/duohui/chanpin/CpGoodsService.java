package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.CpGoodsMapper;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpGoodsAttribute;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.chanpin.CpSpecification;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.system.SysRegionService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    private CpGoodsAttributeService cpGoodsAttributeService;
    @Autowired
    private CpShopService cpShopService;
    @Autowired
    private SysRegionService sysRegionService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public SysResult saveRecord(CpGoods entity) {
        SysResult sysResult = super.saveRecord(entity);
        if(sysResult.getIsSuccess()){
            if(CollectionUtils.isNotEmpty(entity.getSpecificationList())){
                //保存规格
                for (CpSpecification cpSpecification : entity.getSpecificationList()) {
                    cpSpecification.setGoodsId(entity.getId());
                    cpSpecificationService.saveRecord(cpSpecification);
                }
            }

            if(CollectionUtils.isNotEmpty(entity.getGoodsAttributeList())){
                //保存商品规格属性
                for (CpGoodsAttribute cpGoodsAttribute : entity.getGoodsAttributeList()) {
                    cpGoodsAttribute.setGoodsId(entity.getId());
                    cpGoodsAttributeService.saveRecord(cpGoodsAttribute);
                }
            }
        }
        return sysResult;
    }

    @Override
    @SneakyThrows
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public SysResult updateRecordById(CpGoods entity) {
        SysResult sysResult = super.updateRecordById(entity);
        if(sysResult.getIsSuccess()){
            if(CollectionUtils.isNotEmpty(entity.getSpecificationList())){
                //删除原有规格
                boolean deleteResult = cpSpecificationService.deleteBySelective(MapUtil.getMap("goodsId", entity.getId()));
                //保存规格
                for (CpSpecification cpSpecification : entity.getSpecificationList()) {
                    cpSpecification.setGoodsId(entity.getId());
                    cpSpecificationService.saveRecord(cpSpecification);
                }
            }

            if(CollectionUtils.isNotEmpty(entity.getGoodsAttributeList())){
                //删除原有规格
                boolean deleteResult = cpGoodsAttributeService.deleteBySelective(MapUtil.getMap("goodsId", entity.getId()));
                //保存商品规格属性
                for (CpGoodsAttribute cpGoodsAttribute : entity.getGoodsAttributeList()) {
                    cpGoodsAttribute.setGoodsId(entity.getId());
                    cpGoodsAttributeService.saveRecord(cpGoodsAttribute);
                }
            }
        }
        return sysResult;
    }

    @Override
    public CpGoods initialize(CpGoods obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            obj.setCode(UUID.randomUUID().toString().replace("-",""));
            SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
            if(loginUser.getShopId()!=null){
                obj.setShopId(loginUser.getShopId());
                if(obj.getAreaRegionId()==null){
                    CpShop cpShop = cpShopService.getById(loginUser.getShopId());
                    obj.setAreaRegionId(cpShop.getRegionId());
                }
            }

        }else{
            if(obj.getIsAudit()==null)obj.setIsAudit(false);    //如果修改，默认改为未审核
        }
        //赋值地区
        /*if(obj.getAreaRegionId()!=null){
            if(obj.getCityRegionId()==null){
                SysRegion areaRegion = sysRegionService.getById(obj.getAreaRegionId());
                obj.setCityRegionId(areaRegion.getParentId());
                if (obj.getProvinceRegionId() == null) {
                    SysRegion cityRegion = sysRegionService.getById(areaRegion.getParentId());
                    obj.setProvinceRegionId(cityRegion.getParentId());
                }
            }
        }*/
        //赋值行业类型
        /*if(StringUtils.isNotBlank(obj.getThirdryIndustryDcode())){
            if(StringUtils.isBlank(obj.getSecondryIndustryDcode())){
                SysDictionary thirdryIndustryDic = sysDictionaryService.selectOne(MapUtil.getMap("code", obj.getThirdryIndustryDcode()));
                obj.setSecondryIndustryDcode(thirdryIndustryDic.getParentCode());
                if(StringUtils.isBlank(obj.getPrimaryIndustryDcode())){
                    SysDictionary secondryIndustryDic = sysDictionaryService.getById(thirdryIndustryDic.getParentId());
                    obj.setPrimaryIndustryDcode(secondryIndustryDic.getParentCode());
                }

            }
        }*/

        return obj;
    }

    @Override
    public List<CpGoods> parse(List<CpGoods> list) {
        list = super.parse(list);
        for (CpGoods cpGoods : list) {
            List<CpSpecification> specificationList = cpSpecificationService.selectList(MapUtil.getMap("goodsId", cpGoods.getId()));
            cpGoods.setSpecificationList(specificationList);
            List<CpGoodsAttribute> attributeList = cpGoodsAttributeService.selectList(MapUtil.getMap("goodsId", cpGoods.getId()));
            cpGoods.setGoodsAttributeList(attributeList);
            if(StringUtils.isNotBlank(cpGoods.getFileId())){
                String fileUrl = String.valueOf(cpGoods.getMap().get("fileUrl"));
                if(fileUrl.indexOf(",")!=-1){
                    cpGoods.setTitleImgUrl(fileUrl.substring(0,fileUrl.indexOf(",")));
                }else{
                    cpGoods.setTitleImgUrl(fileUrl);
                }

            }
            CpShop cpShop = cpShopService.getById(cpGoods.getShopId());
            cpGoods.setIsOpenShop(cpShop.getIsFacade());
        }
        return list;
    }
}
