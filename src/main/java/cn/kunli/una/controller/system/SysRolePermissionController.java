package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysRolePermission;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysRolePermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * (SysRolePermission)表控制层
 *
 * @author Ponzio
 * @since 2020-05-16 15:32:52
 */
@Controller
@RequestMapping("/sys/rolePermission")
public class SysRolePermissionController extends BaseController<SysRolePermissionService, SysRolePermission> {

    @PutMapping("/batch")
    @ResponseBody
    public SysResult update(@RequestBody List<SysRolePermission> list) {
        if(CollectionUtils.isNotEmpty(list)){
            for (SysRolePermission sysRolePermission : list) {
                if(sysRolePermission.getId()!=null){
                    SysResult sysResult = service.updateRecordById(sysRolePermission);
                    if(!sysResult.getIsSuccess())return sysResult;
                }
            }
        }
        return SysResult.success();
    }
}
