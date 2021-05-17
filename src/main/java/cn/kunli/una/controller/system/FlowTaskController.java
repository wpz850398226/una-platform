package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.flow.FlowTask;
import cn.kunli.una.service.system.FlowTaskService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程任务(FlowTask)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Controller
@RequestMapping("/flow/task")
public class FlowTaskController extends BaseController<FlowTaskService, FlowTask> {
}
