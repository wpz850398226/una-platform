package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.service.system.SysConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysConfiguration)表控制层
 *
 * @author Ponzio
 * @since 2020-05-07 08:53:37
 */
@Controller
@RequestMapping("/sys/configuration")
public class SysConfigurationController extends BaseController<SysConfigurationService, SysConfiguration> {
}
