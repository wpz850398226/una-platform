package cn.kunli.una.controller.system;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.service.system.CpGoodsService;
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
@RequestMapping("/cpGoods")
public class CpGoodsController extends BaseController<CpGoodsService, CpGoods> {
}
