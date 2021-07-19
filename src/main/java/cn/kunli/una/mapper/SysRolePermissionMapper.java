package cn.kunli.una.mapper;

import cn.kunli.una.handler.BasicMapper;
import cn.kunli.una.pojo.system.SysRolePermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * (SysRolePermission)表数据库访问层
 *
 * @author Ponzio
 * @since 2020-05-07 08:56:34
 */

public interface SysRolePermissionMapper extends BasicMapper<SysRolePermission> {

    @Insert("insert into sys_role_permission (role_id,permission_id,scope_dcode) select #{roleId},id,'permission_scope_0' from sys_permission where is_delete=0 order by id")
    Integer insertByRoleId(@Param("roleId") Integer roleId);

    @Insert("insert into sys_role_permission (role_id,permission_id,scope_dcode) select id,#{permissionId},'permission_scope_0' from sys_role where is_delete=0 order by id")
    Integer insertByPermissionId(@Param("permissionId") Integer permissionId);

}
