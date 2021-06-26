package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpOrder;
import cn.kunli.una.service.duohui.chanpin.CpOrderService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-订单类(CpOrder)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:02
 */
@Controller
@RequestMapping("/cp/order")
public class CpOrderController extends BaseController<CpOrderService, CpOrder> {
}
