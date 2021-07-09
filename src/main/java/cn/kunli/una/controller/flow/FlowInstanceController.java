package cn.kunli.una.controller.flow;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.flow.FlowDefinitionService;
import cn.kunli.una.service.flow.FlowInstanceService;
import cn.kunli.una.service.flow.FlowNodeService;
import cn.kunli.una.service.flow.FlowTaskService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
    public SysResult add(@Valid FlowInstance entity) {
        SysResult saveResult = super.save(entity);
        if(saveResult.getIsSuccess()){
//            FlowDefinition flowDefinition = flowDefinitionService.getById(entity.getDefinitionId());
//            if(flowDefinition!=null){
//                flowDefinitionService.updateRecordById(new FlowDefinition().setTotal(flowDefinition.getTotal()+1).setRunning(flowDefinition.getRunning()+1).setId(entity.getDefinitionId()));
//            }

            //保存流程实例成功，查找开始节点
            FlowNode startNode = flowNodeService.getOne(flowNodeService.getWrapper(MapUtil.buildHashMap()
                    .put("definitionId", entity.getDefinitionId())
                    .put("typeDcode", "flow_nudeType_start").build()));

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
                        FlowTask flowTask = new FlowTask().setInstanceId(entity.getId())
                                .setNodeId(flowNode.getId())
                                .setActivateTime(new Date());
                        switch(flowNode.getCandidateTypeDcode()){
                            case "flow_candidateType_account":
                                flowTask.setCandidateAccountId(flowNode.getCandidateValue());
                                break;
                            case "flow_candidateType_role":
                                List<SysAccount> accountList = sysAccountService.selectList(MapUtil.getMap("*:apply", "CONCAT(role_id, ',') REGEXP CONCAT(REPLACE('"+flowNode.getCandidateValue()+"',',',',|'),',') =1"));
                                if(CollectionUtils.isNotEmpty(accountList)){
                                    StringBuffer stringBuffer = new StringBuffer();
                                    for (SysAccount sysAccount : accountList) {
                                        stringBuffer.append(",").append(sysAccount.getId());
                                    }
                                    flowTask.setCandidateAccountId(stringBuffer.delete(0,1).toString());
                                }
                                break;
                            case "flow_candidateType_superior":
                                SysAccount sysAccount = sysAccountService.getById(entity.getCreatorId());
                                flowTask.setCandidateAccountId(sysAccount.getId().toString());
                                break;
                            case "flow_candidateType_departmentManager":
                                break;
                            case "flow_candidateType_initiator":
                                flowTask.setCandidateAccountId(entity.getCreatorId().toString());
                                break;
                        }
                        flowTaskService.saveRecord(flowTask);
                    }
                }
            }
        }
        return saveResult;
    }

}
