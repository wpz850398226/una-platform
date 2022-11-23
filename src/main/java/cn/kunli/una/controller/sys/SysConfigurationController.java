package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysConfiguration;
import cn.kunli.una.service.sys.SysConfigurationService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysConfiguration)表控制层
 *
 * @author Ponzio
 * @since 2020-05-07 08:53:37
 */
@Controller
@Api(tags = "系统-配置")
@RequestMapping("/sys/configuration")
public class SysConfigurationController extends BaseController<SysConfigurationService, SysConfiguration> {
}
