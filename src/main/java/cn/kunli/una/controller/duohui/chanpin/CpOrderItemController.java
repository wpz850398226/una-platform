package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpOrderItem;
import cn.kunli.una.service.duohui.chanpin.CpOrderItemService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-订单明细表(CpOrderDetail)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:03
 */
@Controller
@RequestMapping("/cp/orderItem")
public class CpOrderItemController extends BaseController<CpOrderItemService, CpOrderItem> {
}
