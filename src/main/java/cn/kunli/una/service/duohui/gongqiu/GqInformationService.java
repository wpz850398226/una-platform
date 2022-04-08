package cn.kunli.una.service.duohui.gongqiu;

import cn.kunli.una.mapper.GqInformationMapper;
import cn.kunli.una.pojo.gongqiu.GqInformation;
import cn.kunli.una.pojo.sys.SysCompany;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UserUtil;
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
            if(loginUser.getCompanyId()!=null){
                obj.setCompanyId(loginUser.getCompanyId());
                if(obj.getRegionIds()==null){
                    SysCompany sysCompany = sysCompanyService.getById(loginUser.getCompanyId());
                    obj.setRegionIds(sysCompany.getRegionIds());
                }
            }

        }else{
            if(obj.getIsAudit()==null)obj.setIsAudit(false);    //如果修改，默认改为未审核
        }

        return obj;
    }
}
