package cn.kunli.una.service.system;

import cn.kunli.una.pojo.workflow.WorkflowInstance;
import cn.kunli.una.mapper.WorkflowInstanceMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程实例(WorkflowInstance)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:49
 */
@Service
public class WorkflowInstanceService extends BasicService<WorkflowInstanceMapper, WorkflowInstance> {

    @Autowired
    private WorkflowInstanceService thisProxy;
    @Override
    public BasicService<WorkflowInstanceMapper, WorkflowInstance> getThisProxy() {
        return thisProxy;
    }
}
