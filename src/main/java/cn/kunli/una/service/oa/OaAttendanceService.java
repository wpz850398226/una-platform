package cn.kunli.una.service.oa;

import cn.kunli.una.mapper.OaAttendanceMapper;
import cn.kunli.una.pojo.oa.OaAttendance;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
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
    public SysResult validate(OaAttendance obj) {
        return SysResult.success();
    }

    @Override
    public OaAttendance initialize(OaAttendance obj) {
        if(obj.getId()==null){
            //通过反射赋值"顺序"字段
            obj.setName("未打卡");
        }else{
            SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
            if(loginUser!=null){
                obj.setModifierId(loginUser.getId());
                obj.setModifierName(loginUser.getName());
            }

        }

        return obj;
    }
}
