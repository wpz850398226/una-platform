package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpComment;
import cn.kunli.una.service.duohui.chanpin.CpCommentService;
import cn.kunli.una.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-评论(CpComment)表控制层
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:28
 */
@Controller
@Api(tags = "商城-评论")
@RequestMapping("/cp/comment")
public class CpCommentController extends BaseController<CpCommentService, CpComment> {
}
