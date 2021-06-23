package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.chanpin.CpGoodsParameter;
import cn.kunli.una.service.system.CpGoodsParameterService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-商品参数表(CpGoodsParameter)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:01
 */
@Controller
@RequestMapping("/cpGoodsParameter")
public class CpGoodsParameterController extends BaseController<CpGoodsParameterService, CpGoodsParameter> {
}
