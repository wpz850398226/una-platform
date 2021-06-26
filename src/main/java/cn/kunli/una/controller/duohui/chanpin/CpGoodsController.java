package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号(CpGoods)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Controller
@RequestMapping("/cp/goods")
public class CpGoodsController extends BaseController<CpGoodsService, CpGoods> {
}
