package cn.kunli.una.service.system;

import cn.kunli.una.pojo.flow.FlowDefinition;
import cn.kunli.una.mapper.FlowDefinitionMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程定义(FlowDefinition)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:04:07
 */
@Service
public class FlowDefinitionService extends BasicService<FlowDefinitionMapper, FlowDefinition> {

    @Autowired
    private FlowDefinitionService thisProxy;

    @Override
    public BasicService<FlowDefinitionMapper, FlowDefinition> getThisProxy() {
        return thisProxy;
    }
}
