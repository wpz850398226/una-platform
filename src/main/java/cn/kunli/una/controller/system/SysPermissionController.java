package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysPermission;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysPermissionService;
import cn.kunli.una.utils.common.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * (SysPermission)表控制层
 *
 * @author Ponzio
 * @since 2020-05-10 06:46:06
 */
@Controller
@RequestMapping("/sys/permission")
public class SysPermissionController extends BaseController<SysPermissionService, SysPermission> {

    //token获取用户信息
    @GetMapping("/getByToken")
    @ResponseBody
    public SysResult getByToken() {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        List<String> list = service.selectCodeByUserIdCollection(loginUser.getId());
        return new SysResult().success(list);
    }

}
