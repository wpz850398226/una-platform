package cn.kunli.una.mapper;

import cn.kunli.una.handler.BaseMapper;
import cn.kunli.una.pojo.system.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectTreeBySelective(@Param("record") SysMenu record);
}
