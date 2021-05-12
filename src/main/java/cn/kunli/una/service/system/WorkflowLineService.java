package cn.kunli.una.service.system;

import cn.kunli.una.pojo.workflow.WorkflowLine;
import cn.kunli.una.mapper.WorkflowLineMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程连线(WorkflowLine)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:50
 */
@Service
public class WorkflowLineService extends BasicService<WorkflowLineMapper, WorkflowLine> {

    @Autowired
    private WorkflowLineService thisProxy;
    @Override
    public BasicService<WorkflowLineMapper, WorkflowLine> getThisProxy() {
        return thisProxy;
    }
}
