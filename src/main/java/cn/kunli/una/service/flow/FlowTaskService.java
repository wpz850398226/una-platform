package cn.kunli.una.service.flow;

import cn.kunli.una.mapper.FlowTaskMapper;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.flow.FlowLine;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysDepartment;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.system.SysDepartmentService;
import cn.kunli.una.utils.common.UnaListUtil;
import cn.kunli.una.utils.common.UnaMapUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 流程任务(FlowTask)表服务类
 *
 * @author Ponzio1
 * @since 2021-05-12 22:29:52
 */
@Service
public class FlowTaskService extends BasicService<FlowTaskMapper, FlowTask> {
    @Autowired
    private FlowTaskService thisProxy;
    @Autowired
    private FlowNodeService flowNodeService;
    @Autowired
    private FlowLineService flowLineService;
    @Autowired
    private FlowDefinitionService flowDefinitionService;
    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Override
    public BasicService<FlowTaskMapper, FlowTask> getThisProxy() {
        return thisProxy;
    }

    /**
     * 激活任务
     * @param nodeId
     * @param instanceId
     * @return
     */
    public SysResult activate(Integer nodeId, Integer instanceId){
        FlowTask flowTask = new FlowTask().setInstanceId(instanceId).setNodeId(nodeId).setActivateTime(new Date());
        //如果候选人id为空，则激活失败
        FlowTask initializedTask = this.initialize(flowTask);
        FlowNode flowNode = flowNodeService.getById(nodeId);

        //如果节点有候选人类型，且候选人为空，则返回失败
        if(StrUtil.isNotBlank(flowNode.getCandidateTypeDcode())&&StrUtil.isBlank(initializedTask.getCandidateAccountIds())){
            return SysResult.fail("激活失败，无候选人");
        }

        //激活任务
        SysResult sysResult = thisProxy.saveRecord(flowTask);
        if(sysResult.getIsSuccess()){
            if(StrUtil.isBlank(flowNode.getCandidateTypeDcode())){
                //如果节点没有候选人类型，则执行
                handle((FlowTask) new FlowTask().setId(flowTask.getId()));
            }else{
                //如果有候选人类型，查询候选人id
                String candidateAccountIds = flowTask.getCandidateAccountIds();
                //如果 被激活的任务 的候选人 包含当前在线用户，则返回任务id
                if(StrUtil.isNotBlank(candidateAccountIds)&& candidateAccountIds.contains(String.valueOf(flowTask.getCreatorId()))){
                    sysResult.setData(this.parse(UnaListUtil.getList(flowTask)).get(0));
                }
            }
        }

        return sysResult;
        /*if(flowNode.getTypeDcode().equals("flow_nudeType_end")){
            //如果目标节点是结束类型，直接完成任务，并结束
            SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
            flowTask.setAccountId(loginUser.getId()).setOffTime(new Date());
            thisProxy.saveRecord(flowTask);
            //关闭流程实例
            flowInstanceService.updateRecordById((FlowInstance) new FlowInstance().setIsRunning(false).setId(instanceId));
            return SysResult.success("流程结束");
        }else{
            return thisProxy.saveRecord(flowTask);
        }*/

    }

    /**
     * 处理待办任务
     * @return
     */
    public SysResult handle(FlowTask entity){
        entity.setOffTime(new Date());
        //查询该任务
        FlowTask flowTask = thisProxy.getById(entity.getId());
        if(flowTask.getNodeTypeDcode().equals("flow_nudeType_end")){
            boolean isAgree = true;
            //如果处理的是结束节点，则结束流程实例
            SysResult updateResult = thisProxy.updateRecordById(entity);
            if(updateResult.getIsSuccess()){
                //结束节点，判断所有审批节点是否都审批通过
                Integer instanceId = flowTask.getInstanceId();
                FlowInstance flowInstance = flowInstanceService.getById(instanceId);
                //查询所属流程定义的所有审批节点
                List<FlowNode> flowNodeList = flowNodeService.selectList(UnaMapUtil.buildHashMap()
                        .put("definitionId", flowInstance.getDefinitionId()).put("typeDcode", "flow_nudeType_audit").build());
                //查询同一个流程实例下的所有审批节点，是否都审批通过
                for (FlowNode flowNode : flowNodeList) {
                    FlowTask auditTask = thisProxy.selectOne(UnaMapUtil.buildHashMap()
                            .put("nodeId", flowNode.getId()).put("instanceId", instanceId).build());
                    if(auditTask==null || !auditTask.getIsAgree()){
                        isAgree = false;
                        break;
                    }
                }

                flowInstanceService.updateRecordById((FlowInstance) new FlowInstance().setIsRunning(false).setIsAgree(isAgree).setId(flowTask.getInstanceId()));
                return SysResult.success("流程结束");
            }
        }else{
            //不是结束节点，查询任务后续连线
            List<FlowLine> flowLineList = flowLineService.selectList(UnaMapUtil.getMap("originNodeId", flowTask.getNodeId()));

            if(CollectionUtils.isNotEmpty(flowLineList)){
                //如果连线不为空
                for (FlowLine flowLine : flowLineList) {
                    JSONObject flowCondition = flowLine.getFlowCondition();
                    if(MapUtils.isNotEmpty(flowCondition)){
                        //流程连线设置了条件
                        if(flowCondition.containsKey("isAgree")){
                            //条件是审批是否通过
                            if(!entity.getIsAgree().equals(flowCondition.get("isAgree"))){
                                //不符合条件，跳过该节点
                                break;
                            }
                        }else{
                            //条件是其他参数，暂时跳过
                            break;
                        }
                    }

                    //连线无条件或符合条件，未跳过，执行该节点
                    FlowNode targetNode = flowNodeService.getById(flowLine.getTargetNodeId());
                    //激活任务
                    SysResult activateResult = this.activate(targetNode.getId(), flowTask.getInstanceId());
                    if(!activateResult.getIsSuccess()){
                        //激活失败
                        return activateResult;
                    }
                }
            }else{
                return SysResult.fail("流程配置错误：缺少后续连线");
            }
        }

        //走到这一步，说明所有后续节点都激活成功了
        SysResult updateResult = thisProxy.updateRecordById(entity);

        return updateResult;
    }

    @Override
    public FlowTask initialize(FlowTask obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            //新增任务时，如果是激活待办任务，则查询所有候选人id，并保存
            FlowNode flowNode = flowNodeService.getById(obj.getNodeId());
            FlowInstance flowInstance = flowInstanceService.getById(obj.getInstanceId());
            obj.setNodeTypeDcode(flowNode.getTypeDcode());
//            obj.setNodeEntityId(flowNode.getEntityId());
            obj.setName(flowInstance.getName()+flowNode.getName());

            //流程发起人
            SysAccount initiator = sysAccountService.getById(flowInstance.getCreatorId());

            if(StrUtil.isNotBlank(flowNode.getCandidateTypeDcode())){
                switch(flowNode.getCandidateTypeDcode()){
                    case "flow_candidateType_account":             //账号（复数）
                        obj.setCandidateAccountIds(flowNode.getCandidateValue());
                        break;
                    case "flow_candidateType_role":                //角色（复数）
                        List<SysAccount> accountList = sysAccountService.selectList(UnaMapUtil.getMap("*:apply", "CONCAT(role_id, ',') REGEXP CONCAT(REPLACE('"+flowNode.getCandidateValue()+"',',',',|'),',') =1"));
                        if(CollectionUtils.isNotEmpty(accountList)){
                            StringBuffer stringBuffer = new StringBuffer();
                            for (SysAccount sysAccount : accountList) {
                                stringBuffer.append(",").append(sysAccount.getId());
                            }
                            String accountIds = stringBuffer.toString();
                            if(StrUtil.isNotBlank(accountIds))obj.setCandidateAccountIds(accountIds.substring(1));
                        }
                        break;
                    case "flow_candidateType_superior":            //流程发起人的直接上级
                        obj.setCandidateAccountIds(String.valueOf(initiator.getSuperiorAccountId()));
                        break;
                    case "flow_candidateType_departmentManager":   //部门主管
                        SysDepartment sysDepartment = sysDepartmentService.getById(initiator.getDepartmentId());
                        obj.setCandidateAccountIds(String.valueOf(sysDepartment.getManagerAccountId()));
                        break;
                    case "flow_candidateType_initiator":           //流程发起人
                        obj.setCandidateAccountIds(flowInstance.getCreatorId().toString());
                        break;
                }
            }
        }
        return obj;
    }

    @Override
    public List<FlowTask> parse(List<FlowTask> list) {
        list = super.parse(list);
        for (FlowTask flowTask : list) {
            if(StrUtil.isNotBlank(flowTask.getNodeTypeDcode())&&flowTask.getNodeTypeDcode().equals("flow_nudeType_audit")){
                List<FlowTask> submitTaskList = thisProxy.parse(thisProxy.selectList(UnaMapUtil.buildHashMap().put("instanceId", flowTask.getInstanceId())
                        .put("isNotNull", "offTime").put("isNotNull", "recordId").put("orderByAsc","offTime").build()));
                flowTask.setSubmitTaskList(submitTaskList);
            }
            //查询关联表单
            if(flowTask.getNodeId()!=null){
                FlowNode flowNode = flowNodeService.getById(flowTask.getNodeId());
                if(flowNode!=null){
                    if(MapUtils.isEmpty(flowTask.getMap()))flowTask.setMap(new HashMap<>());
                    flowTask.getMap().put("nodeEntityId",flowNode.getEntityId());
                }
            }
        }
        return list;
    }
}
