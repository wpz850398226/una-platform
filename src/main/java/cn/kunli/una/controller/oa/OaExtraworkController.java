package cn.kunli.una.controller.oa;

import cn.kunli.una.pojo.oa.OaExtrawork;
import cn.kunli.una.service.oa.OaExtraworkService;
import cn.kunli.una.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 办公-加班(OaExtrawork)表控制层
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:22
 */
@Controller
@Api(tags = "办公-加班")
@RequestMapping("/oa/extrawork")
public class OaExtraworkController extends BaseController<OaExtraworkService, OaExtrawork> {
}
