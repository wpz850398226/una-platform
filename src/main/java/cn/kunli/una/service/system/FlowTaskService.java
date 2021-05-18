package cn.kunli.una.service.system;

import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.mapper.FlowTaskMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public BasicService<FlowTaskMapper, FlowTask> getThisProxy() {
        return thisProxy;
    }
}
