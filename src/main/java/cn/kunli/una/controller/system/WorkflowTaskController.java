package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.workflow.WorkflowTask;
import cn.kunli.una.service.system.WorkflowTaskService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程任务(WorkflowTask)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Controller
@RequestMapping("/workflow/task")
public class WorkflowTaskController extends BaseController<WorkflowTaskService, WorkflowTask> {
}
