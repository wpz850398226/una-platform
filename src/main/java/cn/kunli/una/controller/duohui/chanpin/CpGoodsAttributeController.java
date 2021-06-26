package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoodsAttribute;
import cn.kunli.una.service.duohui.chanpin.CpGoodsAttributeService;
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
@RequestMapping("/cp/goodsAttribute")
public class CpGoodsAttributeController extends BaseController<CpGoodsAttributeService, CpGoodsAttribute> {
}
