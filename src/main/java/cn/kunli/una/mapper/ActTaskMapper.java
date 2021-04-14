package cn.kunli.una.mapper;

import cn.kunli.una.handler.BaseMapper;
import cn.kunli.una.pojo.system.SysRole;

import java.util.List;
import java.util.Map;


public interface ActTaskMapper extends BaseMapper<SysRole> {

    //待办id查询组用户id
    List<Map<String,Object>> selectCandidateByTaskId(String taskId);
}
