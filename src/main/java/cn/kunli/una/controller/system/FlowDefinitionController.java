package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.flow.FlowDefinition;
import cn.kunli.una.service.system.FlowDefinitionService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程定义(FlowDefinition)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:04:07
 */
@Controller
@RequestMapping("/flow/definition")
public class FlowDefinitionController extends BaseController<FlowDefinitionService, FlowDefinition> {
}
