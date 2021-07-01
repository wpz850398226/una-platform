package cn.kunli.una.service.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.mapper.CpGoodsMapper;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }


    @Override
    public CpGoods initialize(CpGoods obj) {
        obj = super.initialize(obj);
        if(StringUtils.isNotBlank(obj.getThirdryIndustryDcode())){
            SysDictionary thirdryIndustryDic = sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code", obj.getThirdryIndustryDcode())));
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
}
