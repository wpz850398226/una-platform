package cn.kunli.una.controller.flow;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.flow.FlowDefinitionService;
import cn.kunli.una.service.flow.FlowInstanceService;
import cn.kunli.una.service.flow.FlowNodeService;
import cn.kunli.una.service.flow.FlowTaskService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 流程实例(FlowInstance)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:49
 */
@Controller
@RequestMapping("/flow/instance")
public class FlowInstanceController extends BaseController<FlowInstanceService, FlowInstance> {

    @Autowired
    private FlowNodeService flowNodeService;
    @Autowired
    private FlowTaskService flowTaskService;
    @Autowired
    private FlowDefinitionService flowDefinitionService;

    @PostMapping("")
    @ResponseBody
    public SysResult add(@RequestBody @Valid FlowInstance entity) {
        SysResult saveResult = super.add(entity);
        if(saveResult.getIsSuccess()){
//            FlowDefinition flowDefinition = flowDefinitionService.getById(entity.getDefinitionId());
//            if(flowDefinition!=null){
//                flowDefinitionService.updateRecordById(new FlowDefinition().setTotal(flowDefinition.getTotal()+1).setRunning(flowDefinition.getRunning()+1).setId(entity.getDefinitionId()));
//            }

            //保存流程实例成功，查找开始节点
            FlowNode startNode = flowNodeService.selectOne(MapUtil.buildHashMap()
                    .put("definitionId", entity.getDefinitionId())
                    .put("typeDcode", "flow_nudeType_start").build());

            if(startNode!=null){
                //生成开始流程任务，并直接完成该任务
                flowTaskService.saveRecord(
                        (FlowTask) new FlowTask().setInstanceId(entity.getId())
                                .setAccountId(entity.getCreatorId())
                                .setNodeId(startNode.getId())
                                .setActivateTime(new Date())
                                .setOffTime(new Date())
                                .setName(entity.getName()+"-"+startNode.getName()));
                //查询后续节点
                List<FlowNode> subsequentNodeList = flowNodeService.getSubsequent(startNode.getId());
                if(CollectionUtils.isNotEmpty(subsequentNodeList)){
                    //激活节点，生成待办任务
                    for (FlowNode flowNode : subsequentNodeList) {
                        SysResult activateResult = flowTaskService.activate(flowNode.getId(), entity.getId());
                        if(activateResult.getIsSuccess()){
                            FlowTask flowTask = flowTaskService.getById(String.valueOf(activateResult.getData()));
                            //判断，如果激活的任务候选人包含当前办理人，则立即办理
                            if(StringUtil.containSubString(flowTask.getCandidateAccountId(),String.valueOf(entity.getCreatorId()),null)){
                                Map<String, Object> dataMap = MapUtil.getMap("instanceId", entity.getId());
                                FlowNode taskNode = flowNodeService.getById(flowTask.getNodeId());
                                switch(taskNode.getTypeDcode()){
                                    case "flow_nudeType_audit": //审批类型
                                        dataMap.put("isImmediate", 1);
                                        dataMap.put("taskTypeDcode", "flow_nudeType_audit");
                                        List<FlowTask> flowTaskList = flowTaskService.selectList(MapUtil.buildHashMap().put("instanceId", entity.getId())
                                                .put("isNotNull", "offTime").put("isNotNull", "recordId").put("orderByAsc","offTime").build());
                                        if(CollectionUtils.isNotEmpty(flowTaskList)){
                                            List<Map> mapList = new ArrayList<>();
                                            for (FlowTask task : flowTaskList) {
                                                FlowNode finishedTaskNode = flowNodeService.getById(task.getNodeId());
                                                Map<String, Object> finishedMap = MapUtil.buildHashMap().put("entityId", finishedTaskNode.getEntityId()).put("recordId", task.getRecordId()).build();
                                                mapList.add(finishedMap);
                                            }
                                            dataMap.put("finishedTaskList",mapList);
                                        }
                                        break;
                                    case "flow_nudeType_submit":    //提交类型
                                        dataMap.put("isImmediate", 1);
                                        dataMap.put("entityId", taskNode.getEntityId());
                                        dataMap.put("taskTypeDcode", "flow_nudeType_submit");
                                        break;
                                    default:
                                        dataMap.put("isImmediate", 0);
                                        break;
                                }

                                saveResult.setData(dataMap);
                            }
                        }
                    }
                }
            }
        }
        return saveResult;
    }

}
