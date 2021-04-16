package cn.kunli.una.mapper;

import cn.kunli.una.handler.BasicMapper;
import cn.kunli.una.pojo.system.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysMenuMapper extends BasicMapper<SysMenu> {

    List<SysMenu> selectTreeBySelective(@Param("record") SysMenu record);
}
