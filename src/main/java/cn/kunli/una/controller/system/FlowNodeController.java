package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.service.system.FlowNodeService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程节点(FlowNode)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Controller
@RequestMapping("/flow/node")
public class FlowNodeController extends BaseController<FlowNodeService, FlowNode> {
}
