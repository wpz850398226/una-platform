/*
package cn.kunli.una.utils.activiti;

import cn.kunli.una.pojo.vo.ActTask;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.utils.common.ListUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

*/
/**
 * @author Ponzio
 * @version 2020年2月26日09:06:45
 * 流程引擎 任务工具类
 *//*

@Component
public class TaskUtil {

    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;

//  List<task> list = taskService//与正在执行的任务管理相关的Service
    //    .createTaskQuery()//创建任务查询对象
    */
/**查询条件（where部分）*//*

    //    .taskAssignee(assignee)//指定个人任务查询，指定办理人
    //  .taskCandidateUser(candidateUser)//User组任务的办理人查询
    //  .taskCandidateGroup(candidateGroup)//Group组任务的办理人查询
    //  .processDefinitionId(processDefinitionId)//使用流程定义ID查询
    //  .processInstanceId(processInstanceId)//使用流程实例ID查询
    //  .executionId(executionId)//使用执行对象ID查询
    */
/**排序*//*

    //    .orderByTaskCreateTime().asc()//使用创建时间的升序排列
    */
/**返回结果集*//*

    //  .singleResult()//返回惟一结果集
    //  .count()//返回结果集的数量
    //  .listPage(firstResult, maxResults);//分页查询
    //    .list();//返回列表

    */
/**
     * 通过办理人id查询当前任务数量
     * assignee 办理人id
     *//*

    public Long getTaskCountBySelective(ActTask obj) {
        TaskQuery query = taskService.createTaskQuery();

        List<String> definitionKeyList = new ArrayList<>();
        if(ListUtil.isNotNull(obj.getDefinitionKeyList()))definitionKeyList = obj.getDefinitionKeyList();
        if(StringUtils.isNotBlank(obj.getDefinitionKey())&&obj.getDefinitionKey().indexOf(",")!=-1){
            String[] split = obj.getDefinitionKey().split(",");
            definitionKeyList = Arrays.asList(split);
        }
        //查询条件
        if (obj != null) {
            if (obj.getId()!=null) query.taskId(obj.getId());
            if (StringUtils.isNotBlank(obj.getName())) query.taskName(obj.getName());
            if (StringUtils.isNotBlank(obj.getNameLike())) query.taskNameLike(obj.getNameLike());
            if (StringUtils.isNotBlank(obj.getInstanceId())) query.processInstanceId(obj.getInstanceId());
            if (StringUtils.isNotBlank(obj.getUserId())) query.taskAssignee(obj.getUserId());
            if (StringUtils.isNotBlank(obj.getCandidateUserId())) query.taskCandidateUser(obj.getCandidateUserId());
            if (ListUtil.isNotNull(obj.getCandidateGroupList())) query.taskCandidateGroupIn(obj.getCandidateGroupList());
            if (ListUtil.isNotNull(definitionKeyList)){
                query.processDefinitionKeyIn(definitionKeyList);
            }else{
                if (StringUtils.isNotBlank(obj.getDefinitionKey())) query.processDefinitionKey(obj.getDefinitionKey());
            }
        }
        return query.count();
    }

    */
/**
     * 条件查询待办
     *//*

    public List<Task> selectTaskBySelective(ActTask obj) {
        TaskQuery query = taskService.createTaskQuery();
        List<Task> taskList = new ArrayList<>();
        List<String> definitionKeyList = new ArrayList<>();
        if(ListUtil.isNotNull(obj.getDefinitionKeyList()))definitionKeyList = obj.getDefinitionKeyList();
        if(StringUtils.isNotBlank(obj.getDefinitionKey())&&obj.getDefinitionKey().indexOf(",")!=-1){
            String[] split = obj.getDefinitionKey().split(",");
            definitionKeyList = Arrays.asList(split);
        }
        //查询条件
        if (obj != null) {
            if (obj.getId()!=null) query.taskId(obj.getId());
            if (StringUtils.isNotBlank(obj.getName())) query.taskName(obj.getName());
            if (StringUtils.isNotBlank(obj.getNameLike())) query.taskNameLike(obj.getNameLike());
            if (ListUtil.isNotNull(definitionKeyList)){
                query.processDefinitionKeyIn(definitionKeyList);
            }else{
                if (StringUtils.isNotBlank(obj.getDefinitionKey())) query.processDefinitionKey(obj.getDefinitionKey());
            }
            if (StringUtils.isNotBlank(obj.getInstanceId())) query.processInstanceId(obj.getInstanceId());
            if (StringUtils.isNotBlank(obj.getUserId())) query.taskAssignee(obj.getUserId());
            if (StringUtils.isNotBlank(obj.getCandidateUserId())) query.taskCandidateUser(obj.getCandidateUserId());
            if (ListUtil.isNotNull(obj.getCandidateGroupList())) query.taskCandidateGroupIn(obj.getCandidateGroupList());
        }

        query.orderByTaskCreateTime().desc();
        if (obj != null) {
            int startNum = (obj.getPageNum() - 1) * obj.getPageSize();
            taskList = query.listPage(startNum, obj.getPageSize());
        } else {
            taskList = query.list();
        }
        return taskList;
    }


    */
/**
     * 条件查询已办理任务
     * assignee 办理人id
     *//*

    public List<HistoricTaskInstance> selectHiTaskBySelective(ActTask obj) {
        HistoricTaskInstanceQuery query = historyService
                .createHistoricTaskInstanceQuery();
        List<HistoricTaskInstance> historicTaskInstanceList = new ArrayList<>();

        List<String> definitionKeyList = new ArrayList<>();
        if(ListUtil.isNotNull(obj.getDefinitionKeyList()))definitionKeyList = obj.getDefinitionKeyList();
        if(StringUtils.isNotBlank(obj.getDefinitionKey())&&obj.getDefinitionKey().indexOf(",")!=-1){
            String[] split = obj.getDefinitionKey().split(",");
            definitionKeyList = Arrays.asList(split);
        }

        //查询条件
        if (obj != null) {
            if (obj.getId()!=null) query.taskId(obj.getId());
            if (StringUtils.isNotBlank(obj.getName())) query.taskName(obj.getName());
            if (StringUtils.isNotBlank(obj.getNameLike())) query.taskNameLike(obj.getNameLike());
            if (StringUtils.isNotBlank(obj.getInstanceId()))query.processInstanceId(obj.getInstanceId());
            if (StringUtils.isNotBlank(obj.getUserId())) query.taskAssignee(obj.getUserId());
            if (StringUtils.isNotBlank(obj.getTaskDefinitionKey())) query.taskDefinitionKey(obj.getTaskDefinitionKey());
            if (ListUtil.isNotNull(definitionKeyList)){
                query.processDefinitionKeyIn(definitionKeyList);
            }else{
                if (StringUtils.isNotBlank(obj.getDefinitionKey())) query.processDefinitionKey(obj.getDefinitionKey());
            }
        }
        query.orderByTaskCreateTime().desc();
        if (obj != null) {
            int startNum = (obj.getPageNum() - 1) * obj.getPageSize();
            historicTaskInstanceList = query.listPage(startNum, obj.getPageSize());
        } else {
            historicTaskInstanceList = query.list();
        }

        return historicTaskInstanceList;
    }

    */
/**
     * 条件查询已办数量
     * assignee 办理人id
     *//*

    public Long getHiTaskCountBySelective(ActTask obj) {
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();

        List<String> definitionKeyList = new ArrayList<>();
        if(ListUtil.isNotNull(obj.getDefinitionKeyList()))definitionKeyList = obj.getDefinitionKeyList();
        if(StringUtils.isNotBlank(obj.getDefinitionKey())&&obj.getDefinitionKey().indexOf(",")!=-1){
            String[] split = obj.getDefinitionKey().split(",");
            definitionKeyList = Arrays.asList(split);
        }
        //查询条件
        if (obj != null) {
            if (obj.getId()!=null) query.taskId(obj.getId());
            if (StringUtils.isNotBlank(obj.getName())) query.taskName(obj.getName());
            if (StringUtils.isNotBlank(obj.getNameLike())) query.taskNameLike(obj.getNameLike());
            if (StringUtils.isNotBlank(obj.getInstanceId()))query.processInstanceId(obj.getInstanceId());
            if (StringUtils.isNotBlank(obj.getUserId())) query.taskAssignee(obj.getUserId());
            if (StringUtils.isNotBlank(obj.getTaskDefinitionKey())) query.taskDefinitionKey(obj.getTaskDefinitionKey());
            if (ListUtil.isNotNull(definitionKeyList)){
                query.processDefinitionKeyIn(definitionKeyList);
            }else{
                if (StringUtils.isNotBlank(obj.getDefinitionKey())) query.processDefinitionKey(obj.getDefinitionKey());
            }
        }

        return query.count();
    }


    */
/**
     * 通过任务id查询当前任务
     * id 任务id
     *//*

    public Task getTaskById(String id) {
        //根据assignee(代理人)查询任务
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        return task;
    }

    */
/**
     * 通过任务id查询历史任务
     * id 任务id
     *//*

    public HistoricTaskInstance getHiTaskById(String id) {

        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery()
                .taskId(id).singleResult();
        return historicTaskInstance;
    }

    */
/**
     * 设置局部流程变量
     *//*

    public SysResult setVariableLocal(String taskId, Map<String, Object> map) {
        try {
            taskService.setVariablesLocal(taskId, map);
            return SysResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.fail(e.getMessage());
        }

    }


    */
/**
     * 将指定任务进行委托处理
     *//*

    public SysResult delegateTask(String taskId, String assignee) {
        try {
            taskService.delegateTask(taskId, assignee);
            return SysResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.fail(e.getMessage());
        }

    }
    */
/**
     * 认领将指定任务
     *//*

    public SysResult setAssignee(String taskId, String assignee) {
        try {
            taskService.setAssignee(taskId,assignee);
            return SysResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.fail(e.getMessage());
        }
    }
    */
/**
     * 根据taskId解决被委托的任务,动态传值设置变量
     *//*

    public SysResult setglobalVariable(String taskId, Map<String, Object> globalVariableMap){
        try {

            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if (task.getOwner() != null && !"".equals(task.getOwner())) {
                DelegationState delegationState = task.getDelegationState();
                if ("RESOLVED".equals(delegationState.toString())) {
                    return SysResult.build(201, "此委托任务已是完结状态");
                } else if ("PENDING".equals(delegationState.toString())) {
                    //如果是委托任务需要做处理
                    taskService.resolveTask(taskId, globalVariableMap);
                    return SysResult.success();
                } else {
                    return SysResult.build(201, "此任务不是委托任务");
                }
            }
            return SysResult.build(201, "设置失败");
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.fail(e.getMessage());
        }
    }

    */
/**
     * 办理任务
     *//*

    public SysResult handleTask(String taskId, Map<String, Object> map) {
        try {
            //根据上一步生成的taskId执行任务
            if(map.get("comments")!=null){
                String[] comments = map.get("comments").toString().split(",");
                for (String comment : comments) {
                    taskService.addComment(taskId,null,comment);
                }
            }
            map.remove("comments");

            taskService.complete(taskId, map);
            return SysResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.fail(e.getMessage());
        }

    }

    */
/**
     * 撤回任务
     *//*

    */
/*public SysResult recallTask(String taskId) {


        RuntimeService runtimeService = processEngine.getRuntimeService();

        RepositoryService repositoryService = processEngine.getRepositoryService();


        try {

            Map variables;

            // 取得当前要撤回的任务
            HistoricTaskInstance currTask = historyService.createHistoricTaskInstanceQuery()
                    .taskId(taskId).singleResult();

            // 取得流程实例
            ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(currTask.getProcessInstanceId()).singleResult();

            //获取流程变量
            variables = runtimeService.getVariables(currTask.getExecutionId());

            // 取得流程定义

            ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)

                    .getDeployedProcessDefinition(currTask.getProcessDefinitionId());

            // 取得当前活动

            ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)

                    .findActivity(currTask.getTaskDefinitionKey());

            // 取得下一步活动（复数）

            List<PvmTransition> nextTransitionList = currActivity.getOutgoingTransitions();

            int i = 0;

            for (PvmTransition nextTransition : nextTransitionList) {

                //下一步活动节点
                PvmActivity nextActivity = nextTransition.getDestination();

                //获取已经完成的下一步待办
                List completeTasks = historyService.createHistoricTaskInstanceQuery()

                        .processInstanceId(instance.getId()).taskDefinitionKey(nextActivity.getId()).finished().list();

                int finished = completeTasks.size();

                if (finished > 0) {//下一环节已经完成

                    HistoricTaskInstance historicTaskInstance = (HistoricTaskInstance) completeTasks.get(0);
                    return SysResult.build(201, "撤回失败,下个节点：" + historicTaskInstance.getName() + "已处理", null);

                }

                List<Task> nextTasks = taskService.createTaskQuery().processInstanceId(instance.getId())

                        .taskDefinitionKey(nextActivity.getId()).list();

                for (Task nextTask : nextTasks) {

                    String idList = variables.get(nextTask.getTaskDefinitionKey() + "Users").toString();

                    if (!StringUtil.isEmpty(idList)) {

                        String[] strs = idList.split(",");

                        //taskService.getVariable(nextTask.getId(), nextTask.getTaskDefinitionKey()+"_users");

                        // 取活动，清除活动方向

                        List<PvmTransition> oriPvmTransitionList = new ArrayList();

                        List<PvmTransition> pvmTransitionList = nextActivity.getOutgoingTransitions();

                        for (PvmTransition pvmTransition : pvmTransitionList) {

                            oriPvmTransitionList.add(pvmTransition);

                        }

                        pvmTransitionList.clear();

                        // 建立新方向

                        ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)

                                .findActivity(nextTask.getTaskDefinitionKey());

                        TransitionImpl newTransition = nextActivityImpl.createOutgoingTransition();

                        newTransition.setDestination(currActivity);

                        // 完成任务

                        taskService.claim(nextTask.getId(), strs[i]);

                        i++;

                        taskService.complete(nextTask.getId(), variables);

                        historyService.deleteHistoricTaskInstance(nextTask.getId());

                        // 恢复方向

                        currActivity.getIncomingTransitions().remove(newTransition);

                        List<PvmTransition> pvmTList = nextActivity.getOutgoingTransitions();

                        pvmTList.clear();

                        for (PvmTransition pvmTransition : oriPvmTransitionList) {

                            pvmTransitionList.add(pvmTransition);

                        }

                    }

                }

            }

            historyService.deleteHistoricTaskInstance(currTask.getId());

            return SysResult.build(200, "撤回成功", null);

        } catch (Exception e) {

            e.printStackTrace();

            return SysResult.build(201, "撤回失败", e.getMessage());

        }

    }*//*



    */
/**
     * 通过任务id获取流程变量
     *
     * @param id   任务id
     * @param name 流程变量名
     *//*

    public Object getVariableByTaskIdAndName(String id, String name) {
        try {
            //根据上一步生成的taskId执行任务
            Object obj = taskService.getVariable(id, name);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    */
/**
     * 通过任务id获取所有流程变量
     *
     * @param id 任务id
     *//*

    public Map<String, Object> getVariableByTaskId(String id) {
        try {
            //根据上一步生成的taskId执行任务
            Map<String, Object> obj = taskService.getVariables(id);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    */
/**
     * 通过流程实例id获取当前流程任务
     *
     * @param instanceId 流程实例id
     * @return
     *//*

    public List<Task> getTaskByInstanceId(String instanceId) {
        try {
            List<Task> taskList = taskService
                    .createTaskQuery().processInstanceId(instanceId).list();
            return taskList;
        } catch (Exception e) {
            return null;
        }
    }


    */
/**
     * 通过任务id与变量名获取单个流程局部变量
     *
     * @param taskId    任务id
     * @param paramName 变量名
     * @return
     *//*

    public SysResult getVariableLocalByTaskIdAndParamName(String taskId, String paramName) {
        try {
            Object object = taskService.getVariableLocal(taskId, paramName);
            return new SysResult().success(object);
        } catch (Exception e) {
            return SysResult.fail(e.getMessage());
        }
    }

    */
/**
     * 通过任务id获取所有流程局部变量
     *
     * @param taskId 任务id
     * @return
     *//*

    public Map<String, Object> getVariablesLocalByTaskId(String taskId) {
        try {
            Map<String, Object> map = taskService.getVariablesLocal(taskId);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    */
/**
     * 通过任务id获取所有流程局部变量
     *
     * @param taskId 任务id
     * @return
     *//*

    public List<HistoricVariableInstance> getVariablesLocalByHiTaskId(String taskId) {
        try {
            List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().taskId(taskId).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    */
/**
     * 通过任务id获取所有流程局部变量
     *
     * @param id
     * @return
     *//*

    public List<Comment> getCommentByTaskId(String id) {
        try {

            List<Comment> taskComments = taskService.getTaskComments(id);
            return taskComments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    */
/**
     * 通过办理人id查询当前任务数量
     * assignee 办理人id
     *//*

    public SysResult claim(ActTask obj) {
        try {
            taskService.claim(obj.getId(),obj.getUserId());
            return SysResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.fail("任务领取失败，可能已被其他人处理");
        }

    }


    */
/**
     * 流程转向操作
     *
     * @throws Exception
     *//*

    public SysResult turnTransition(ActTask obj){

        try {
            // 当前节点
            ActivityImpl currActivity = findActivitiImpl(obj.getId(), null);
            // 清空当前流向
            List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

            // 创建新流向
            TransitionImpl newTransition = currActivity.createOutgoingTransition();
            // 目标节点
            ActivityImpl pointActivity = findActivitiImpl(obj.getId(), obj.getActivityId());
            // 设置新流向的目标节点
            newTransition.setDestination(pointActivity);

            //设置任务批注
            if(StringUtils.isNotBlank(obj.getComments())){
                String[] commentArray = obj.getComments().toString().split(",");
                for (String comment : commentArray) {
                    taskService.addComment(obj.getId(),null,comment);
                }
            }

            //签收任务
            taskService.setAssignee(obj.getId(),obj.getUserId());

            // 执行转向任务
            taskService.complete(obj.getId(), obj.getParam());
            // 删除目标节点新流入
            pointActivity.getIncomingTransitions().remove(newTransition);

            // 还原以前流向
            restoreTransition(currActivity, oriPvmTransitionList);
            return SysResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.fail(e.getMessage());
        }

    }


    */
/**
     * 根据任务ID和节点ID获取活动节点 <br>
     *
     * @param taskId
     *            任务ID
     * @param activityId
     *            活动节点ID <br>
     *            如果为null或""，则默认查询当前活动节点 <br>
     *            如果为"end"，则查询结束节点 <br>
     *
     * @return
     * @throws Exception
     *//*

    private ActivityImpl findActivitiImpl(String taskId, String activityId)
            throws Exception {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

        // 获取当前活动节点ID
        if (StringUtils.isBlank(activityId)) {
            activityId = findTaskById(taskId).getTaskDefinitionKey();
        }

        // 根据流程定义，获取该流程实例的结束节点
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl
                        .getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }

        // 根据节点ID，获取对应的活动节点
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
                .findActivity(activityId);

        return activityImpl;
    }

    */
/**
     * 清空指定活动节点流向
     *
     * @param activityImpl
     *            活动节点
     * @return 节点流向集合
     *//*

    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        // 获取当前节点所有流向，存储到临时变量，然后清空
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    */
/**
     * 还原指定活动节点流向
     *
     * @param activityImpl
     *            活动节点
     * @param oriPvmTransitionList
     *            原有节点流向集合
     *//*

    private void restoreTransition(ActivityImpl activityImpl,
                                   List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }

    */
/**
     * 根据任务ID获取流程定义
     *
     * @param taskId
     *            任务ID
     * @return
     * @throws Exception
     *//*

    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
            String taskId) throws Exception {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(findTaskById(taskId)
                        .getProcessDefinitionId());

        if (processDefinition == null) {
            throw new Exception("流程定义未找到!");
        }

        return processDefinition;
    }

    */
/**
     * 根据任务ID获得任务实例
     *
     * @param taskId
     *            任务ID
     * @return
     * @throws Exception
     *//*

    private TaskEntity findTaskById(String taskId) throws Exception {
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(
                taskId).singleResult();
        if (task == null) {
            throw new Exception("任务实例未找到!");
        }
        return task;
    }


}
*/
