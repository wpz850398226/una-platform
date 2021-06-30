package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysRole;
import cn.kunli.una.pojo.system.SysRolePermission;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysRolePermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * (SysRolePermission)表控制层
 *
 * @author Ponzio
 * @since 2020-05-16 15:32:52
 */
@Controller
@RequestMapping("/sys/rolePermission")
public class SysRolePermissionController extends BaseController<SysRolePermissionService, SysRolePermission> {

}
