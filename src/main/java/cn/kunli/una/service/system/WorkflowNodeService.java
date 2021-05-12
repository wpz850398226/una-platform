package cn.kunli.una.service.system;

import cn.kunli.una.pojo.workflow.WorkflowNode;
import cn.kunli.una.mapper.WorkflowNodeMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程节点(WorkflowNode)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:51
 */
@Service
public class WorkflowNodeService extends BasicService<WorkflowNodeMapper, WorkflowNode> {
    @Autowired
    private WorkflowNodeService thisProxy;
    @Override
    public BasicService<WorkflowNodeMapper, WorkflowNode> getThisProxy() {
        return thisProxy;
    }
}
