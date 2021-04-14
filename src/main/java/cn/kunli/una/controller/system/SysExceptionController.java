package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysException;
import cn.kunli.una.service.system.SysExceptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysException)表控制层
 *
 * @author Ponzio
 * @since 2020-07-21 11:04:55
 */
@Controller
@RequestMapping("/sys/sysException")
public class SysExceptionController extends BaseController<SysExceptionService, SysException> {

}
