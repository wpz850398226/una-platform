package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysMessage;
import cn.kunli.una.service.system.SysMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysMessage)表控制层
 *
 * @author Ponzio
 * @since 2020-06-05 11:38:30
 */
@Controller
@RequestMapping("/sys/message")
public class SysMessageController extends BaseController<SysMessageService, SysMessage> {

}
