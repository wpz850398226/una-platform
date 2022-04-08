package cn.kunli.una.mapper;

import cn.kunli.una.handler.BasicMapper;
import cn.kunli.una.pojo.sys.SysRole;

import java.util.List;
import java.util.Map;


public interface ActTaskMapper extends BasicMapper<SysRole> {

    //待办id查询组用户id
    List<Map<String,Object>> selectCandidateByTaskId(String taskId);
}
