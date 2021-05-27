package cn.kunli.una.service.flow;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.FlowDefinitionMapper;
import cn.kunli.una.pojo.flow.FlowDefinition;
import cn.kunli.una.pojo.flow.FlowNode;
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
    @Autowired
    private FlowNodeService flowNodeService;

    @Override
    public BasicService<FlowDefinitionMapper, FlowDefinition> getThisProxy() {
        return thisProxy;
    }

    /**
     * 根据主键进行查询
     * @param entity
     * @return
     */
    @Override
    @MyCacheEvict(value = "list")
    public boolean save(FlowDefinition entity) {
        boolean saveResult = super.save(entity);
        if(saveResult){
            //如果流程定义保存成功，自动新增开始和结束节点
            flowNodeService.save(flowNodeService.initialize((FlowNode) new FlowNode().setDefinitionId(entity.getId()).setTypeDcode("platform_flow_nudeType_start").setName("开始")));
            flowNodeService.save(flowNodeService.initialize((FlowNode) new FlowNode().setDefinitionId(entity.getId()).setTypeDcode("platform_flow_nudeType_end").setName("结束")));
        }
        return saveResult;
    }
}
