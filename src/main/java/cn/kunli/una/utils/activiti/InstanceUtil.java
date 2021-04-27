/*
package cn.kunli.una.utils.activiti;

import cn.kunli.una.pojo.vo.ActInstance;
import cn.kunli.una.pojo.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

*/
/**
 * @author Ponzio
 * @version 2020年2月26日09:06:45
 * 流程引擎工具类
 *//*

@Slf4j
@Component
public class InstanceUtil {

    */
/**
     * 会默认按照Resources目录下的activiti.cfg.xml创建流程引擎
     *//*

    //private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;


    */
/**
     * 查询单个历史流程实例
     *//*

    public HistoricProcessInstance getHiInstanceByInstanceId(String id) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(id).singleResult();
    }


    */
/**
     * 分页查询历史流程实例
     *//*

    public List<HistoricProcessInstance> findHistoricProcessInstanceInPageBySelective(ActInstance obj) {
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
        if(obj!=null){
            if(obj.getId()!=null)query.processInstanceId(obj.getId());
            if(StringUtils.isNotBlank(obj.getDefinitionId()))query.processDefinitionId(obj.getDefinitionId());
            if(StringUtils.isNotBlank(obj.getName()))query.processInstanceNameLike(obj.getName());
            if(StringUtils.isNotBlank(obj.getKey()))query.processInstanceBusinessKey(obj.getKey());
            if(StringUtils.isNotBlank(obj.getDefinitionKey()))query.processDefinitionKey(obj.getDefinitionKey());
        }
        List<HistoricProcessInstance> list = query.orderByProcessInstanceStartTime().desc().listPage(obj.getPageNum() - 1, obj.getPageSize());
        return list;
    }


    */
/**
     * 启动工作流,启动流程实例
     * @param processDefinitionId 部署了的流程id
     * @param map 代办组或者代办人 格式 users:******* (条件)
     * @return 启动的工作流id
     * @Author : Ponzio. create at 2020年3月4日07:49:39
     *//*

    public String startInstanceById(String processDefinitionId, Map<String, Object> map) {
        log.info("【启动工作流】processDefinitionId={},users={}", processDefinitionId, map);
        // 5.获取流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinitionId, map);
        return pi.getId();
    }

    */
/**
     * 启动工作流
     * @param processDefinitionKey 部署了的流程key
     * @param map 代办组或者代办人 格式 users:******* (条件)
     * @return 启动的工作流id
     * @Author : Ponzio. create at 2020年3月4日07:49:35
     *  
     *//*

    public SysResult startInstanceByKey(String processDefinitionKey, Map<String, Object> map) {
        log.info("【启动工作流】processDefinitionKey={},users={}", processDefinitionKey, map);
        // 5.获取流程实例
        try {
            ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
            return new SysResult().success("启动成功",pi.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.fail(e.getMessage());
        }

    }

    */
/**
     * 通过流程实例id获取流程变量
     * @param instanceId 流程实例id
     * @return
     *//*

    public Map<String,Object> getVariablesByInstanceId(String instanceId) {
        try {
            Map<String,Object> map = runtimeService.getVariables(instanceId);
            return map;
        } catch (Exception e) {
            return null;
        }

    }

    */
/**
     * 条件获取历史流程变量
     *
     * @param obj
     *//*

    public List<HistoricVariableInstance> getHiVariablesByInstanceId(ActInstance obj) {
        try {
            HistoricVariableInstanceQuery historicVariableInstanceQuery = historyService.createHistoricVariableInstanceQuery();

            //指定流程实例id
            if (obj.getId()!=null)historicVariableInstanceQuery.processInstanceId(obj.getId());
            //指定变量名
            if (StringUtils.isNotBlank(obj.getVariableName()))historicVariableInstanceQuery.variableName(obj.getVariableName());

            List<HistoricVariableInstance> historicVariableInstanceList = historicVariableInstanceQuery.list();
            return historicVariableInstanceList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    */
/**
     * 通过流程实例id获取历史活动节点
     * @param instanceId 流程实例id
     * @return
     *//*

    public List<HistoricActivityInstance> getHistoricActivityByInstanceId(String instanceId) {
        try {
            List<HistoricActivityInstance> list = historyService
                    .createHistoricActivityInstanceQuery()
                    .processInstanceId(instanceId)
                    .activityType("userTask")   //默认查询任务节点类型
                    .orderByHistoricActivityInstanceStartTime()
                    .asc().finished().list();
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    */
/**
     * 通过流程实例id获取流程实例
     * @param instanceId 流程实例id
     * @return
     *//*

    public ProcessInstance getInstanceByInstanceId(String instanceId) {
        try {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
            return processInstance;
        } catch (Exception e) {
            return null;
        }

    }

    */
/**
     * 通过流程实例id终止流程
     * @param instanceId 流程实例id
     * @return
     *//*

    public SysResult deleteInstanceById(String instanceId) {
        try {
            runtimeService.deleteProcessInstance(instanceId,null);
            return SysResult.success();
        } catch (Exception e) {
            return SysResult.fail();
        }

    }

}
*/
