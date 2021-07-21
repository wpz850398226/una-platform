package cn.kunli.una.controller.duohui.guanwang;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.guanwang.GwConfiguration;
import cn.kunli.una.service.duohui.guanwang.GwConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 配置(GwConfiguration)表控制层
 *
 * @author Ponzio
 * @since 2021-03-26 11:26:06
 */
@Controller
@RequestMapping("/gw/configuration")
public class GwConfigurationController extends BaseController<GwConfigurationService, GwConfiguration> {
}
