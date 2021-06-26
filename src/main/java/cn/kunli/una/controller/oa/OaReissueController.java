package cn.kunli.una.controller.oa;

import cn.kunli.una.pojo.oa.OaReissue;
import cn.kunli.una.service.oa.OaReissueService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 办公-补卡(OaReissue)表控制层
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:23
 */
@Controller
@RequestMapping("/oa/reissue")
public class OaReissueController extends BaseController<OaReissueService, OaReissue> {
}
