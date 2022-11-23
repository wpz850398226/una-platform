package cn.kunli.una.controller.oa;

import cn.kunli.una.pojo.oa.OaTravel;
import cn.kunli.una.service.oa.OaTravelService;
import cn.kunli.una.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 办公-出差(OaTravel)表控制层
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:25
 */
@Controller
@Api(tags = "办公-出差")
@RequestMapping("/oa/travel")
public class OaTravelController extends BaseController<OaTravelService, OaTravel> {
}
