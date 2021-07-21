package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpComment;
import cn.kunli.una.service.system.CpCommentService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-评论(CpComment)表控制层
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:28
 */
@Controller
@RequestMapping("/cp/comment")
public class CpCommentController extends BaseController<CpCommentService, CpComment> {
}
