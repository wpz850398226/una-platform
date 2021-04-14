package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysArticle;
import cn.kunli.una.service.system.SysArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (SysArticle)表控制层
 *
 * @author Ponzio
 * @since 2020-05-06 17:13:09
 */
@Controller
@RequestMapping("/sys/article")
public class SysArticleController extends BaseController<SysArticleService, SysArticle> {
}
