package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpDeliveryAddress;
import cn.kunli.una.service.duohui.chanpin.CpDeliveryAddressService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-收件人(CpDeliveryAddress)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Controller
@RequestMapping("/cp/deliveryAddress")
public class CpDeliveryAddressController extends BaseController<CpDeliveryAddressService, CpDeliveryAddress> {
}
