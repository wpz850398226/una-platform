package cn.kunli.una.controller.oa;

import cn.kunli.una.pojo.oa.OaJourney;
import cn.kunli.una.service.oa.OaJourneyService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 办公-出差行程(OaJourney)表控制层
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:23
 */
@Controller
@RequestMapping("/oa/journey")
public class OaJourneyController extends BaseController<OaJourneyService, OaJourney> {
}
