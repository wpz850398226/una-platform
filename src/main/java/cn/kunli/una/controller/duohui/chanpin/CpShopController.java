package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-店铺类(CpShop)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:04
 */
@Controller
@RequestMapping("/cp/shop")
public class CpShopController extends BaseController<CpShopService, CpShop> {
}
