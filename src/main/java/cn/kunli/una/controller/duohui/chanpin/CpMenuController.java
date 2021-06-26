package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpMenu;
import cn.kunli.una.service.duohui.chanpin.CpMenuService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单(CpMenu)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:02
 */
@Controller
@RequestMapping("/cp/menu")
public class CpMenuController extends BaseController<CpMenuService, CpMenu> {
}
