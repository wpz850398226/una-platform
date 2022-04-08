package cn.kunli.una.service.flow;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.FlowDefinitionMapper;
import cn.kunli.una.pojo.flow.FlowDefinition;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private FlowInstanceService flowInstanceService;

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
    public SysResult saveRecord(FlowDefinition entity) {
        SysResult sysResult = super.saveRecord(entity);
        if(sysResult.getIsSuccess()){
            //如果流程定义保存成功，自动新增开始和结束节点
            flowNodeService.saveRecord((FlowNode) new FlowNode().setDefinitionId(entity.getId()).setTypeDcode("flow_nudeType_start").setName("开始"));
            flowNodeService.saveRecord((FlowNode) new FlowNode().setDefinitionId(entity.getId()).setTypeDcode("flow_nudeType_end").setName("结束"));
        }
        return sysResult;
    }

    @Override
    public List<FlowDefinition> parse(List<FlowDefinition> list) {
        list = super.parse(list);
        for (FlowDefinition flowDefinition : list) {
            flowDefinition.setTotal(flowInstanceService.selectCount(UnaMapUtil.getMap("definitionId",flowDefinition.getId())));
            flowDefinition.setRunning(flowInstanceService.selectCount(UnaMapUtil.buildHashMap().put("definitionId",flowDefinition.getId()).put("isRunning",true).build()));
        }
        return list;
    }
}
