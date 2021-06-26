package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpCart;
import cn.kunli.una.service.duohui.chanpin.CpCartService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-购物车(CpCart)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:39:02
 */
@Controller
@RequestMapping("/cp/cart")
public class CpCartController extends BaseController<CpCartService, CpCart> {
}
