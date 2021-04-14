package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysImgConfig;
import cn.kunli.una.service.system.SysImgConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysImgConfig)表控制层
 *
 * @author Ponzio
 * @since 2020-06-10 11:26:08
 */
@Controller
@RequestMapping("/sys/imgConfig")
public class SysImgConfigController extends BaseController<SysImgConfigService, SysImgConfig> {
}
