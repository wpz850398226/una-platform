package cn.kunli.una.handler;

import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.vo.SysParamMap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicMapper<T extends BasePojo> extends BaseMapper<T> {

    List<T> selectBySelective(@Param("record") SysParamMap map);
}
