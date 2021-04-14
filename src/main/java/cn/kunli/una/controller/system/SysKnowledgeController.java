package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysKnowledge;
import cn.kunli.una.service.system.SysKnowledgeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysKnowledge)表控制层
 *
 * @author Ponzio
 * @since 2020-10-16 17:48:03
 */
@Controller
@RequestMapping("/sys/knowledge")
public class SysKnowledgeController extends BaseController<SysKnowledgeService, SysKnowledge> {
}
