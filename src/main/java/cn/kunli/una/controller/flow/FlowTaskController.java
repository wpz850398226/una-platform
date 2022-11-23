package cn.kunli.una.controller.flow;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.flow.FlowTaskService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 流程任务(FlowTask)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Controller
@Api(tags = "流程-任务")
@RequestMapping("/flow/task")
public class FlowTaskController extends BaseController<FlowTaskService, FlowTask> {

    //办理任务
    @PutMapping("/handle")
    @ResponseBody
    public SysResult handle(@RequestBody FlowTask entity) {
        return service.handle(entity);
    }
}
