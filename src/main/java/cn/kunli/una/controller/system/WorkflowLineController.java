package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.workflow.WorkflowLine;
import cn.kunli.una.service.system.WorkflowLineService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程连线(WorkflowLine)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:50
 */
@Controller
@RequestMapping("/workflow/line")
public class WorkflowLineController extends BaseController<WorkflowLineService, WorkflowLine> {
}
