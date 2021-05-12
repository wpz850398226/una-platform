package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.workflow.WorkflowInstance;
import cn.kunli.una.service.system.WorkflowInstanceService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程实例(WorkflowInstance)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:49
 */
@Controller
@RequestMapping("/workflow/instance")
public class WorkflowInstanceController extends BaseController<WorkflowInstanceService, WorkflowInstance> {
}
