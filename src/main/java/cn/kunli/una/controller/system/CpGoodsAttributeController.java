package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.chanpin.CpGoodsAttribute;
import cn.kunli.una.service.system.CpGoodsAttributeService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城-商品规格表(CpGoodsAttribute)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:01
 */
@Controller
@RequestMapping("/cpGoodsAttribute")
public class CpGoodsAttributeController extends BaseController<CpGoodsAttributeService, CpGoodsAttribute> {
}
