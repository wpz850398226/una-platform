package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysRolePermission;
import cn.kunli.una.service.system.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysRolePermission)表控制层
 *
 * @author Ponzio
 * @since 2020-05-16 15:32:52
 */
@Controller
@RequestMapping("/sys/rolePermission")
public class SysRolePermissionController extends BaseController<SysRolePermissionService, SysRolePermission> {
    @Autowired
    private SysRolePermissionService objService;

    /**
     * 批量查询
     * @return
     */
    /*@Override
	@RequestMapping("/queryPlural")
	@ResponseBody
	public List<SysRolePermission> queryPlural(SysRolePermission obj) {
    	return super.queryPlural(obj);
	}*/


}
