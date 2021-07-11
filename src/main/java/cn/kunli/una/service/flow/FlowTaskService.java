package cn.kunli.una.service.flow;

import cn.kunli.una.mapper.FlowTaskMapper;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysDepartment;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.system.SysDepartmentService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private FlowInstanceService flowInstanceService;
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
        FlowNode flowNode = flowNodeService.getById(nodeId);

        if(flowNode.getTypeDcode().equals("flow_nudeType_end")){
            //如果目标节点是结束类型，直接完成任务，并结束
            SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
            flowTask.setAccountId(loginUser.getId()).setOffTime(new Date());
            getThisProxy().saveRecord(flowTask);
            //关闭流程实例
            flowInstanceService.updateRecordById((FlowInstance) new FlowInstance().setIsRunning(false).setId(instanceId));
            return SysResult.success("待办处理成功，流程结束");
        }else{
            return getThisProxy().saveRecord(flowTask);
        }

    }

    @Override
    public FlowTask initialize(FlowTask obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            //新增任务时，如果是激活待办任务，则查询所有候选人id，并保存
            FlowNode flowNode = flowNodeService.getById(obj.getNodeId());
            obj.setNodeTypeDcode(flowNode.getTypeDcode());
            obj.setNodeEntityId(flowNode.getEntityId());
            FlowInstance flowInstance = flowInstanceService.getById(obj.getInstanceId());
            //流程发起人
            SysAccount initiator = sysAccountService.getById(flowInstance.getCreatorId());

            if(StringUtils.isNotBlank(flowNode.getCandidateTypeDcode())){
                switch(flowNode.getCandidateTypeDcode()){
                    case "flow_candidateType_account":             //账号（复数）
                        obj.setCandidateAccountId(flowNode.getCandidateValue());
                        break;
                    case "flow_candidateType_role":                //角色（复数）
                        List<SysAccount> accountList = sysAccountService.selectList(MapUtil.getMap("*:apply", "CONCAT(role_id, ',') REGEXP CONCAT(REPLACE('"+flowNode.getCandidateValue()+"',',',',|'),',') =1"));
                        if(CollectionUtils.isNotEmpty(accountList)){
                            StringBuffer stringBuffer = new StringBuffer();
                            for (SysAccount sysAccount : accountList) {
                                stringBuffer.append(",").append(sysAccount.getId());
                            }
                            obj.setCandidateAccountId(stringBuffer.delete(0,1).toString());
                        }
                        break;
                    case "flow_candidateType_superior":            //流程发起人的直接上级
                        obj.setCandidateAccountId(initiator.getSuperiorAccountId().toString());
                        break;
                    case "flow_candidateType_departmentManager":   //部门主管
                        SysDepartment sysDepartment = sysDepartmentService.getById(initiator.getDepartmentId());
                        obj.setCandidateAccountId(sysDepartment.getManagerAccountId().toString());
                        break;
                    case "flow_candidateType_initiator":           //流程发起人
                        obj.setCandidateAccountId(flowInstance.getCreatorId().toString());
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
            if(StringUtils.isNotBlank(flowTask.getNodeTypeDcode())&&flowTask.getNodeTypeDcode().equals("flow_nudeType_audit")){
                List<FlowTask> submitTaskList = getThisProxy().selectList(MapUtil.buildHashMap().put("instanceId", flowTask.getInstanceId())
                        .put("isNotNull", "offTime").put("isNotNull", "recordId").put("orderByAsc","offTime").build());
                flowTask.setSubmitTaskList(submitTaskList);
            }
        }
        return list;
    }
}
