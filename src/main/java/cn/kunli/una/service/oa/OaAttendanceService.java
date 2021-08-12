package cn.kunli.una.service.oa;

import cn.kunli.una.mapper.OaAttendanceMapper;
import cn.kunli.una.pojo.oa.OaAttendance;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.RequestUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 办公-考勤(OaAttendance)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:20
 */
@Service
public class OaAttendanceService extends BasicService<OaAttendanceMapper, OaAttendance> {
    @Autowired
    private OaAttendanceService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public OaAttendance initialize(OaAttendance obj) {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        if(obj.getId()==null){
            //通过反射赋值"顺序"字段
        }else{
            if(loginUser!=null){
                obj.setModifierId(loginUser.getId());
                obj.setModifierName(loginUser.getName());
            }

            obj.setModifierHost(RequestUtil.getIpAddress(request));
        }

        return obj;
    }
}
