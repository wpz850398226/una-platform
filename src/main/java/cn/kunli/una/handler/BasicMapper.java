package cn.kunli.una.handler;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface BasicMapper<T extends BasePojo> extends BaseMapper<T> {

//    List<T> selectBySelective(@Param("record") Map<String,Object> map);
}
