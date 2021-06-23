package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.chanpin.CpOrderDetail;
import cn.kunli.una.service.system.CpOrderDetailService;
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
@RequestMapping("/cpOrderDetail")
public class CpOrderDetailController extends BaseController<CpOrderDetailService, CpOrderDetail> {
}
