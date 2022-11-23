package cn.kunli.una.service.sys;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.SysRolePermissionMapper;
import cn.kunli.una.pojo.sys.SysAuthorization;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysAuthorization)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-07 08:56:39
 */
@Service
public class SysAuthorizationService extends BasicService<SysRolePermissionMapper, SysAuthorization> {

    @Autowired
    private SysAuthorizationService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @MyCacheEvict(value = "list")
    public Integer insertByPermissionId(Integer permissionId) {
        return mapper.insertByPermissionId(permissionId);
    }

    @MyCacheEvict(value = "list")
    public Integer insertByRoleId(Integer roleId) { return mapper.insertByRoleId(roleId); }
}
