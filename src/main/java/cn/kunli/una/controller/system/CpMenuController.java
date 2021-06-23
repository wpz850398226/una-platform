package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.chanpin.CpMenu;
import cn.kunli.una.service.system.CpMenuService;
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
@RequestMapping("/cpMenu")
public class CpMenuController extends BaseController<CpMenuService, CpMenu> {
}
