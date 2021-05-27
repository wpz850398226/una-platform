package cn.kunli.una.controller.flow;

import cn.kunli.una.pojo.flow.FlowLine;
import cn.kunli.una.service.flow.FlowLineService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程连线(FlowLine)表控制层
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:50
 */
@Controller
@RequestMapping("/flow/line")
public class FlowLineController extends BaseController<FlowLineService, FlowLine> {
}
