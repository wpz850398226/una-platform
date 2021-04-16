package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysRolePermissionMapper;
import cn.kunli.una.pojo.system.SysRolePermission;
import cn.kunli.una.service.BasicService;
import org.springframework.stereotype.Service;

/**
 * (SysRolePermission)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-07 08:56:39
 */
@Service
public class SysRolePermissionService extends BasicService<SysRolePermissionMapper, SysRolePermission> {

    public Integer insertByPermissionId(Integer permissionId) {
        return mapper.insertByPermissionId(permissionId);
    }

    public Integer insertByRoleId(Integer roleId) {
        return mapper.insertByRoleId(roleId);
    }
}
