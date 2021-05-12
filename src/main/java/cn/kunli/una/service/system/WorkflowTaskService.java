package cn.kunli.una.service.system;

import cn.kunli.una.pojo.workflow.WorkflowTask;
import cn.kunli.una.mapper.WorkflowTaskMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程任务(WorkflowTask)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Service
public class WorkflowTaskService extends BasicService<WorkflowTaskMapper, WorkflowTask> {
    @Autowired
    private WorkflowTaskService thisProxy;
    @Override
    public BasicService<WorkflowTaskMapper, WorkflowTask> getThisProxy() {
        return thisProxy;
    }
}
