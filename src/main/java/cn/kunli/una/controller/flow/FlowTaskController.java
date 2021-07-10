package cn.kunli.una.controller.flow;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.flow.FlowLine;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.flow.FlowInstanceService;
import cn.kunli.una.service.flow.FlowLineService;
import cn.kunli.una.service.flow.FlowNodeService;
import cn.kunli.una.service.flow.FlowTaskService;
import cn.kunli.una.utils.common.MapUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

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
    public SysResult handle(@RequestBody FlowTask entity) {
        entity.setOffTime(new Date());
        super.update(entity);

        FlowTask flowTask = service.getById(entity.getId());
        //查询任务后续连线
        List<FlowLine> flowLineList = flowLineService.selectList(MapUtil.getMap("originNodeId", flowTask.getNodeId()));
        StringBuffer stringBuffer = new StringBuffer();

        for (FlowLine flowLine : flowLineList) {
            JSONObject flowCondition = flowLine.getFlowCondition();
            if(MapUtils.isEmpty(flowCondition)){
                //连线无条件执行
                FlowNode targetNode = flowNodeService.getById(flowLine.getTargetNodeId());
                //激活任务
                SysResult activateResult = service.activate(targetNode.getId(), flowTask.getInstanceId());
                if(activateResult.getIsSuccess()){
                    if(activateResult.getData()!=null){
                        stringBuffer.append(",").append(activateResult.getData());
                    }else{
                        return activateResult;
                    }
                }else{
                    return activateResult;
                }
            }else{
                if(flowCondition.containsKey("isAgree")){
                    //条件是审批是否通过
                    if(entity.getIsAgree().equals(flowCondition.get("isAgree"))){
                        //符合条件，查询连线的目标节点，激活待办任务
                        FlowNode targetNode = flowNodeService.getById(flowLine.getTargetNodeId());
                        //激活任务
                        SysResult activateResult = service.activate(targetNode.getId(), flowTask.getInstanceId());
                        if(activateResult.getIsSuccess()){
                            if(activateResult.getData()!=null){
                                stringBuffer.append(",").append(activateResult.getData());
                            }else{
                                return activateResult;
                            }
                        }else{
                            return activateResult;
                        }
                    }
                }else{
                    //条件是其他参数
                    return SysResult.fail("需要判断其他参数条件");
                }
            }
        }
        return SysResult.success();
    }
}
