package cn.kunli.una.controller.duohui.toubiao;

import cn.kunli.una.pojo.bid.BidProject;
import cn.kunli.una.service.duohui.toubiao.BidProjectService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 招投标-项目表表(BidProject)表控制层
 *
 * @author Ponzio
 * @since 2021-07-17 13:19:47
 */
@Controller
@RequestMapping("/bid/project")
public class BidProjectController extends BaseController<BidProjectService, BidProject> {
}
