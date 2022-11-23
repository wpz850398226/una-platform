package cn.kunli.una.controller.duohui.guanwang;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.guanwang.GwMenu;
import cn.kunli.una.service.duohui.guanwang.GwMenuService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单(GwMenu)表控制层
 *
 * @author Ponzio
 * @since 2021-03-26 11:09:52
 */
@Controller
@Api(tags = "官网-菜单")
@RequestMapping("/gw/menu")
public class GwMenuController extends BaseController<GwMenuService, GwMenu> {
}
