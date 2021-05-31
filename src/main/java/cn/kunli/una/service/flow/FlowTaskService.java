package cn.kunli.una.service.flow;

import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.mapper.FlowTaskMapper;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.system.SysAccountService;
import cn.kunli.una.service.system.SysRoleService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private FlowInstanceService flowInstanceService;
    @Override
    public BasicService<FlowTaskMapper, FlowTask> getThisProxy() {
        return thisProxy;
    }

    @Override
    public FlowTask initialize(FlowTask obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            //新增任务时，如果是激活待办任务，则查询所有候选人id，并保存
            FlowNode flowNode = flowNodeService.getById(obj.getNodeId());
            FlowInstance flowInstance = flowInstanceService.getById(obj.getInstanceId());

            switch(flowNode.getTypeDcode()){
                case "platform_flow_candidateType_account":             //账号（复数）
                    obj.setCandidateAccountId(flowNode.getCandidateValue());
                    break;
                case "platform_flow_candidateType_role":                //角色（复数）
                    String roleIds = flowNode.getCandidateValue();
                    if(StringUtils.isNotBlank(roleIds)){
                        String[] roleIdArray = roleIds.split(",");
                        for (String roleId : roleIdArray) {
                            List<SysAccount> accountList = sysAccountService.list(sysAccountService.getWrapper(MapUtil.getMap("in:roleIds", roleId)));
                            String idStrByPojoList = ListUtil.getIdStrByPojoList(accountList);
                            obj.setCandidateAccountId(idStrByPojoList);
                        }
                    }
                    break;
                case "platform_flow_candidateType_superior":            //直接上级

                    break;
                case "platform_flow_candidateType_departmentManager":   //部门主管
                    break;
                case "platform_flow_candidateType_initiator":           //流程发起人
                    break;
            }
        }
        return obj;
    }
}
