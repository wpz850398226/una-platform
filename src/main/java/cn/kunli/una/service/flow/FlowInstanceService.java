package cn.kunli.una.service.flow;

import cn.kunli.una.pojo.flow.FlowDefinition;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.mapper.FlowInstanceMapper;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    @Autowired
    private FlowDefinitionService flowDefinitionService;
    @Override
    public BasicService<FlowInstanceMapper, FlowInstance> getThisProxy() {
        return thisProxy;
    }

    @Override
    public FlowInstance initialize(FlowInstance obj) {
        obj = super.initialize(obj);
        if(obj.getId()==null){
            FlowDefinition flowDefinition = flowDefinitionService.getById(obj.getDefinitionId());
            if(flowDefinition!=null){
                obj.setName(flowDefinition.getName()+"-"+obj.getCreatorName()+"-"+ TimeUtil.getStrOfDate(new Date()));
            }
        }

        return obj;
    }
}
