package cn.kunli.una.controller.duohui.gongqiu;

import cn.kunli.una.pojo.gongqiu.GqInformation;
import cn.kunli.una.service.system.GqInformationService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 供求信息(GqInformation)表控制层
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:31
 */
@Controller
@RequestMapping("/gq/information")
public class GqInformationController extends BaseController<GqInformationService, GqInformation> {
}
