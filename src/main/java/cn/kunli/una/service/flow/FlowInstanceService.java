package cn.kunli.una.service.flow;

import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.mapper.FlowInstanceMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程实例(FlowInstance)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:49
 */
@Service
public class FlowInstanceService extends BasicService<FlowInstanceMapper, FlowInstance> {

    @Autowired
    private FlowInstanceService thisProxy;
    @Override
    public BasicService<FlowInstanceMapper, FlowInstance> getThisProxy() {
        return thisProxy;
    }
}
