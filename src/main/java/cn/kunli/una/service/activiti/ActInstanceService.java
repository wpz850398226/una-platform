/*
package cn.kunli.una.service.activiti;

import cn.kunli.una.mapper.ActTaskMapper;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.pojo.vo.ActInstance;
import cn.kunli.una.pojo.vo.ActTask;
import cn.kunli.una.utils.activiti.InstanceUtil;
import cn.kunli.una.utils.activiti.TaskUtil;
import cn.kunli.una.utils.common.ListUtil;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * activiti流程实例管理
 *//*

@Service
public class ActInstanceService {
    @Autowired
    private ActTaskService actTaskService;
    @Autowired
    private ActTaskMapper actTaskMapper;

    @Autowired
    private InstanceUtil instanceUtil;
    @Autowired
    private TaskUtil taskUtil;


    */
/**
     * 启动工作流程
     *
     * @param map 流程变量
     * @return
     *//*

    public SysResult save(String definitionKey, Map<String, Object> map) {
        if (definitionKey != null) {
            //流程变量中保存流程定义key
            map.put("definitionKey", definitionKey);
            return instanceUtil.startInstanceByKey(definitionKey, map);
        } else {
            return SysResult.fail();
        }
    }

    */
/**
     * 终止流程
     *
     * @param key
     * @return
     *//*

    public SysResult stop(String key) {
        if (StringUtils.isBlank(key)) return SysResult.fail();
        return instanceUtil.deleteInstanceById(key);
    }


    */
/**
     * 分页条件查询流程实例
     *
     * @return
     *//*

    public SysResult table(ActInstance obj) {
        List<HistoricProcessInstance> list = instanceUtil.findHistoricProcessInstanceInPageBySelective(obj);
        List<Map<String, Object>> maps = ListUtil.entityListToMapList(list);
        for (int i = 0; i < list.size(); i++) {
            maps.get(i).put("instanceId", list.get(i).getId());
        }
        return new SysResult().success(maps);
    }


    */
/**
     * 查询流程变量
     *
     * @return
     *//*

    public SysResult getVariablesByInstanceId(String id) {
        Map<String, Object> variables = instanceUtil.getVariablesByInstanceId(id);
        return new SysResult().success(variables);
    }

    */
/**
     * 查询历史流程变量
     *
     * @return
     *//*

    public SysResult getHiVariablesByInstanceId(String id) {
        List<HistoricVariableInstance> hiVariableByInstanceId = instanceUtil.getHiVariablesByInstanceId(new ActInstance().setId(id));
        return new SysResult().success(ListUtil.entityListToMapList(hiVariableByInstanceId));
    }


    */
/**
     * 查询历史活动节点
     *
     * @return
     *//*

    public SysResult getHistoricActivityByInstanceId(String id) {
        List<HistoricActivityInstance> list = instanceUtil.getHistoricActivityByInstanceId(id);

        if (ListUtil.isNotNull(list)) {
            List<Map<String, Object>> maps = ListUtil.entityListToMapList(list);

            for (int i = 0; i < list.size(); i++) {
                List<Comment> commentList = taskUtil.getCommentByTaskId(list.get(i).getTaskId());
                Map<String, Object> map = new HashMap<>();
                for (Comment comment : commentList) {
                    if (comment.getFullMessage().indexOf("结果") != -1) {
                        map.put("result", comment.getFullMessage().replace("结果：", ""));
                    } else if (comment.getFullMessage().indexOf("意见") != -1) {
                        map.put("opinion", comment.getFullMessage().replace("意见：", ""));
                    } else {
                        map.put("unknown", comment.getFullMessage());
                    }
                }

                maps.get(i).put("processInstanceId", list.get(i).getProcessInstanceId());
                maps.get(i).put("comments", map);
            }
            return new SysResult().success(maps);
        } else {
            return SysResult.success("查询无记录");
        }
    }

    */
/**
     * 查询单个流程实例
     *
     * @return
     *//*

    public SysResult getInstanceByInstanceId(String id) {
        ProcessInstance instance = instanceUtil.getInstanceByInstanceId(id);
        if (instance != null) {
            Map<String, Object> map = ListUtil.entityToMap(instance);
            map.put("instanceId", instance.getId());
            map.remove("sourceActivityExecution");
            return new SysResult().success(map);
        } else {
            return SysResult.fail();
        }
    }

    */
/**
     * 查询单个历史流程实例
     *
     * @return
     *//*

    public SysResult<HistoricProcessInstance> getHiInstanceByInstanceId(String id) {
        HistoricProcessInstance hiInstance = instanceUtil.getHiInstanceByInstanceId(id);
        if (hiInstance != null) {
            return new SysResult().success(hiInstance);
        } else {
            return SysResult.success();
        }
    }


    */
/**
     * 通过流程实例id查询当前待办组任务用户
     *
     * @return
     *//*

    public List<Map<String, Object>> getCandidateUserByInstanceId(String id) {
        List<Map<String, Object>> userMapList = new ArrayList<>();
        //查询当前待办
        SysResult taskPageResult = actTaskService.getTaskBySelective(new ActTask().setInstanceId(id));
        if (taskPageResult.getData() instanceof String) {
            return userMapList;
        }
        Map<String, Object> taskPage = (Map<String, Object>) taskPageResult.getData();
        List<Map<String, Object>> taskMapList = (List<Map<String, Object>>) taskPage.get("list");
        //遍历待办任务
        for (Map<String, Object> taskMap : taskMapList) {
            //获取用户任务的名称

            List<Map<String, Object>> candidateList = actTaskMapper.selectCandidateByTaskId(taskMap.get("taskId").toString());

            for (Map<String, Object> candidateMap : candidateList) {
                Map<String, Object> map = new HashMap<>();
                map.put("taskId", taskMap.get("taskId"));
                map.put("userId", candidateMap.get("USER_ID_"));
                userMapList.add(map);
            }

        }
        return userMapList;
    }

}
*/
