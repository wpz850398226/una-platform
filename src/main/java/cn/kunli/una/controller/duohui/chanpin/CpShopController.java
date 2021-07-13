package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 商城-店铺类(CpShop)表控制层
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:04
 */
@Controller
@RequestMapping("/cp/shop")
public class CpShopController extends BaseController<CpShopService, CpShop> {

    @Autowired
    private CpGoodsService cpGoodsService;

    /**
     * 打开前端 商品详情
     * @param model
     * @return
     */
    @RequestMapping("/fDetail/{id}")
    public String fDetail(Model model, @PathVariable Integer id) {
        CpShop record = service.parse(ListUtil.getList(service.getById(id))).get(0);
        model.addAttribute("record",record);

        List<CpGoods> goodsList = cpGoodsService.selectList(MapUtil.getMap("shopId", record.getId()));
        model.addAttribute("goodsList",cpGoodsService.parse(goodsList));

        return "duohui/dianpu/index";
    }

    //刷新
    @PutMapping("/refresh/{id}")
    @ResponseBody
    public SysResult refresh(@PathVariable Integer id) {
        return service.updateRecordById((CpShop) new CpGoods().setRefreshTime(new Date()).setId(id));
    }

    //置顶
    @PutMapping("/stick/{id}")
    @ResponseBody
    public SysResult stick(@PathVariable Integer id) {
        CpShop byId = service.getById(id);
        return service.updateRecordById((CpShop) new CpGoods().setStickDeadline(TimeUtil.getNextDay(byId.getStickDeadline(),1)).setId(id));
    }
}
