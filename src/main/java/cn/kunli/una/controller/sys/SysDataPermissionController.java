package cn.kunli.una.controller.sys;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysDataPermission;
import cn.kunli.una.service.sys.SysDataPermissionService;
import io.swagger.annotations.Api;

/**
 * <p>
 * 数据权限 控制器
 * </p>
 *
 * @author wangpz
 * @since 2022-09-08
 */
@RestController
@Api(value="数据权限前端控制器")
@RequestMapping("/sys/dataPermission")
public class SysDataPermissionController extends BaseController<SysDataPermissionService, SysDataPermission> {
}
