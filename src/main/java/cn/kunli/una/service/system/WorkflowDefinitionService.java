package cn.kunli.una.service.system;

import cn.kunli.una.pojo.workflow.WorkflowDefinition;
import cn.kunli.una.mapper.WorkflowDefinitionMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程定义(WorkflowDefinition)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:04:07
 */
@Service
public class WorkflowDefinitionService extends BasicService<WorkflowDefinitionMapper, WorkflowDefinition> {

    @Autowired
    private WorkflowDefinitionService thisProxy;

    @Override
    public BasicService<WorkflowDefinitionMapper, WorkflowDefinition> getThisProxy() {
        return thisProxy;
    }
}
