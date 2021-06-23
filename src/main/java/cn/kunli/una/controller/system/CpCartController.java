package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.chanpin.CpCart;
import cn.kunli.una.service.system.CpCartService;
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
@RequestMapping("/cpCart")
public class CpCartController extends BaseController<CpCartService, CpCart> {
}
