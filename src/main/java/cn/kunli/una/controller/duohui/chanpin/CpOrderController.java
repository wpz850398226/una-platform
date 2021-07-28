package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpCart;
import cn.kunli.una.pojo.chanpin.CpOrder;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpOrderService;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商城-订单类(CpOrder)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:02
 */
@Controller
@RequestMapping("/cp/order")
public class CpOrderController extends BaseController<CpOrderService, CpOrder> {

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/zhifu")
    public String fDetail(Model model) {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();

        return "duohui/chanpin/zhifu";
    }
}
