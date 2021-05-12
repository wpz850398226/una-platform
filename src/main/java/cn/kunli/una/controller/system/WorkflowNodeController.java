package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.workflow.WorkflowNode;
import cn.kunli.una.service.system.WorkflowNodeService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程节点(WorkflowNode)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Controller
@RequestMapping("/workflow/node")
public class WorkflowNodeController extends BaseController<WorkflowNodeService, WorkflowNode> {
}
