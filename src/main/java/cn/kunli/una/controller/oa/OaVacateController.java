package cn.kunli.una.controller.oa;

import cn.kunli.una.pojo.oa.OaVacate;
import cn.kunli.una.service.oa.OaVacateService;
import cn.kunli.una.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 办公-请假(OaVacate)表控制层
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:26
 */
@Controller
@Api(tags = "办公-请假")
@RequestMapping("/oa/vacate")
public class OaVacateController extends BaseController<OaVacateService, OaVacate> {
}
