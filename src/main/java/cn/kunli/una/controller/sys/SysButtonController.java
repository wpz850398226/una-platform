package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysButton;
import cn.kunli.una.service.sys.SysButtonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysButton)表控制层
 *
 * @author Ponzio
 * @since 2020-05-07 08:10:24
 */
@Controller
@RequestMapping("/sys/button")
public class SysButtonController extends BaseController<SysButtonService, SysButton> {
}
