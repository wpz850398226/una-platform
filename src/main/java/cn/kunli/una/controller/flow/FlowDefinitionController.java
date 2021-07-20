package cn.kunli.una.controller.flow;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.flow.FlowDefinition;
import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.flow.FlowDefinitionService;
import cn.kunli.una.service.flow.FlowInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * 流程定义(FlowDefinition)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:04:07
 */
@Controller
@RequestMapping("/flow/definition")
public class FlowDefinitionController extends BaseController<FlowDefinitionService, FlowDefinition> {

    @Autowired
    private FlowInstanceService flowInstanceService;

    //流程结束回调函数
    @GetMapping("/{code}/{id}")
    @ResponseBody
    public SysResult finish(@PathVariable String code,@PathVariable Serializable id) {
        FlowInstance flowInstance = flowInstanceService.getById(id);
        switch(code){
            case "businessTrip"://出差申请

                break;
            default:
                break;
        }
        return SysResult.success();
    }
}
