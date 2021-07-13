package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.mapper.CpGoodsMapper;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpGoodsAttribute;
import cn.kunli.una.pojo.chanpin.CpSpecification;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
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
    private CpGoodsAttributeService cpGoodsAttributeService;

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
    public CpGoods initialize(CpGoods obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            obj.setCode(UUID.randomUUID().toString().replace("-",""));
        }
        if(StringUtils.isNotBlank(obj.getThirdryIndustryDcode())){
            SysDictionary thirdryIndustryDic = sysDictionaryService.selectOne(MapUtil.getMap("code", obj.getThirdryIndustryDcode()));
            if(thirdryIndustryDic!=null){
                SysDictionary secondryIndustryDic = sysDictionaryService.getById(thirdryIndustryDic.getParentId());
                if(secondryIndustryDic!=null){
                    obj.setSecondryIndustryDcode(secondryIndustryDic.getCode());
                    SysDictionary primaryIndustryDic = sysDictionaryService.getById(secondryIndustryDic.getParentId());
                    if(primaryIndustryDic!=null){
                        obj.setPrimaryIndustryDcode(primaryIndustryDic.getCode());
                    }
                }

            }
        }

        return obj;
    }

    @Override
    public List<CpGoods> parse(List<CpGoods> list) {
        list = super.parse(list);
        for (CpGoods cpGoods : list) {
            List<CpSpecification> specificationList = cpSpecificationService.selectList(MapUtil.getMap("goodsId", cpGoods.getId()));
            cpGoods.setSpecificationList(specificationList);
        }
        return list;
    }
}
