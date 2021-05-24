package cn.kunli.una.service.system;

import cn.kunli.una.mapper.FlowNodeMapper;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程节点(FlowNode)表服务类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:51
 */
@Service
public class FlowNodeService extends BasicService<FlowNodeMapper, FlowNode> {
    @Autowired
    private FlowNodeService thisProxy;
    @Override
    public BasicService<FlowNodeMapper, FlowNode> getThisProxy() {
        return thisProxy;
    }
}
