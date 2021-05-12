package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.workflow.WorkflowDefinition;
import cn.kunli.una.service.system.WorkflowDefinitionService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程定义(WorkflowDefinition)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:04:07
 */
@Controller
@RequestMapping("/workflow/definition")
public class WorkflowDefinitionController extends BaseController<WorkflowDefinitionService, WorkflowDefinition> {
}
