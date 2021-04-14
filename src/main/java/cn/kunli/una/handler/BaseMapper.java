package cn.kunli.una.handler;

import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.vo.SysParamMap;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseMapper<T extends BasePojo> extends Mapper<T> {

    List<T> selectBySelective(@Param("record") SysParamMap map);
}
