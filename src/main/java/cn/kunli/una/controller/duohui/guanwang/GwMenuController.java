package cn.kunli.una.controller.duohui.guanwang;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.guanwang.GwMenu;
import cn.kunli.una.service.duohui.guanwang.GwMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单(GwMenu)表控制层
 *
 * @author Ponzio
 * @since 2021-03-26 11:09:52
 */
@Controller
@RequestMapping("/gw/menu")
public class GwMenuController extends BaseController<GwMenuService, GwMenu> {
}
