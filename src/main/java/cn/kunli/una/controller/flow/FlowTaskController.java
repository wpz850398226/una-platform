package cn.kunli.una.controller.flow;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.flow.FlowLine;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.flow.FlowInstanceService;
import cn.kunli.una.service.flow.FlowLineService;
import cn.kunli.una.service.flow.FlowNodeService;
import cn.kunli.una.service.flow.FlowTaskService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 流程任务(FlowTask)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Controller
@RequestMapping("/flow/task")
public class FlowTaskController extends BaseController<FlowTaskService, FlowTask> {

    @Autowired
    private FlowLineService flowLineService;
    @Autowired
    private FlowNodeService flowNodeService;
    @Autowired
    private FlowInstanceService flowInstanceService;

    //办理任务
    @PutMapping("/handle")
    @ResponseBody
    public SysResult handle(FlowTask entity) {
        super.update(entity);

        FlowTask flowTask = service.getById(entity.getId());
        List<FlowLine> flowLineList = flowLineService.list(flowLineService.getWrapper(MapUtil.getMap("originNodeId", flowTask.getNodeId())));
        FlowNode flowNode = flowNodeService.getById(flowTask.getNodeId());

        for (FlowLine flowLine : flowLineList) {
            Map<String, Object> flowCondition = flowLine.getFlowCondition();
            if(MapUtils.isNotEmpty(flowCondition)){
                if(flowCondition.containsKey("isAgree")){
                    if(entity.getIsAgree().equals(flowCondition.get("isAgree"))){
                        //符合条件，查询连线的目标节点，激活待办任务
                        FlowNode targetNode = flowNodeService.getById(flowLine.getTargetNodeId());
                        if(targetNode.getTypeDcode().equals("platform_flow_nudeType_end")){
                            //如果目标节点是结束类型，直接完成任务，并结束
                            service.saveRecord(new FlowTask().setInstanceId(entity.getId())
                                    .setAccountId(flowTask.getModifierId())
                                    .setNodeId(targetNode.getId())
                                    .setActivateTime(new Date())
                                    .setOffTime(new Date()));

                            flowInstanceService.updateRecordById((FlowInstance) new FlowInstance().setIsRunning(false).setId(flowTask.getInstanceId()));
                            return SysResult.success("待办处理成功，流程结束");
                        }else{
                            service.saveRecord(new FlowTask().setInstanceId(flowTask.getInstanceId())
                                    .setNodeId(targetNode.getId()).setActivateTime(new Date()));
                            //如果出口排他，则只出一条符合条件的连线
                            if(flowNode.getIsExitExclusive()){
                                return SysResult.success("待办处理成功");
                            }
                        }
                    }
                }
            }
        }

        return SysResult.fail();
    }
}
