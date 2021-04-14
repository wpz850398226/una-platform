package cn.kunli.una.mapper;

import cn.kunli.una.handler.BaseMapper;
import cn.kunli.una.pojo.system.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<String> selectCodeByUserIdCollection(@Param("userId") Integer userId);
}
