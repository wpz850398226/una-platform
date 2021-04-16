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

    @Insert("insert into sys_role_permission (role_id,permission_id,scope) select #{roleId},id,0 from sys_permission order by id")
    Integer insertByRoleId(@Param("roleId") Integer roleId);

    @Insert("insert into sys_role_permission (role_id,permission_id,scope) select id,#{permissionId},0 from sys_role order by id")
    Integer insertByPermissionId(@Param("permissionId") Integer permissionId);

}
