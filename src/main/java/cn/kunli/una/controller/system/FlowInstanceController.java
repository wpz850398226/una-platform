package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.flow.FlowInstance;
import cn.kunli.una.service.system.FlowInstanceService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程实例(FlowInstance)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:49
 */
@Controller
@RequestMapping("/flow/instance")
public class FlowInstanceController extends BaseController<FlowInstanceService, FlowInstance> {
}
