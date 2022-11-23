package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysUser;
import cn.kunli.una.service.sys.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户(SysUser)表控制层
 *
 * @author Ponzio
 * @since 2021-03-14 21:01:36
 */
@Controller
@Api(tags = "系统-用户")
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUserService, SysUser> {

}
