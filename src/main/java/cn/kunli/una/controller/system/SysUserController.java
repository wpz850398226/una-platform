package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysUser;
import cn.kunli.una.service.system.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户(SysUser)表控制层
 *
 * @author Ponzio
 * @since 2021-03-14 21:01:36
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUserService, SysUser> {

}
