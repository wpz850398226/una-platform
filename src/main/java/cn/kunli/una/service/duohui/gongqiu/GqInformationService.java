package cn.kunli.una.service.duohui.gongqiu;

import cn.kunli.una.mapper.GqInformationMapper;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.gongqiu.GqInformation;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 供求信息(GqInformation)表服务类
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:31
 */
@Service
public class GqInformationService extends BasicService<GqInformationMapper, GqInformation> {
    @Autowired
    private GqInformationService thisProxy;
    @Autowired
    private CpShopService cpShopService;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public GqInformation initialize(GqInformation obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            obj.setCode(UUID.randomUUID().toString().replace("-",""));
            SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
            obj.setMobile(loginUser.getUsername());
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

        //赋值行业类型
        if(StringUtils.isNotBlank(obj.getThirdryIndustryDcode())){
            if(StringUtils.isBlank(obj.getSecondryIndustryDcode())){
                SysDictionary thirdryIndustryDic = sysDictionaryService.selectOne(MapUtil.getMap("code", obj.getThirdryIndustryDcode()));
                obj.setSecondryIndustryDcode(thirdryIndustryDic.getParentCode());
                if(StringUtils.isBlank(obj.getPrimaryIndustryDcode())){
                    SysDictionary secondryIndustryDic = sysDictionaryService.getById(thirdryIndustryDic.getParentId());
                    obj.setPrimaryIndustryDcode(secondryIndustryDic.getParentCode());
                }

            }
        }
        return obj;
    }
}
