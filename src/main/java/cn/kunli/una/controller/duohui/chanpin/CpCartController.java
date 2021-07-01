package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpCart;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.service.duohui.chanpin.CpCartService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/fDetail")
    public String fDetail(Model model) {
        return "duohui/chanpin/gwc";
    }

}
