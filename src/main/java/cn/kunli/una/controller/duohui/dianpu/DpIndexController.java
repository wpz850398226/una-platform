package cn.kunli.una.controller.duohui.dianpu;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysMenuService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/duohui/dianpu")
public class DpIndexController {
    @Autowired
    private CpShopService cpShopService;
    @Autowired
    private CpGoodsService cpGoodsService;

    /**
     * 打开店铺主页
     *
     * @param model
     * @return
     */
    @GetMapping("/index/{id}")
    public String index(Model model,@PathVariable Integer id) {
        Page<CpGoods> goodsPage = cpGoodsService.page(1L, 10L, MapUtil.getMap("shopId", id));
        CpShop record = cpShopService.parse(ListUtil.getList(cpShopService.getById(id))).get(0);
        model.addAttribute("record",record);
        model.addAttribute("goodsList",cpGoodsService.parse(goodsPage.getRecords()));

        return "duohui/dianpu/index";
    }

    /**
     * 打开店铺主页
     *
     * @param model
     * @return
     */
    @GetMapping("/about/{id}")
    public String about(Model model,@PathVariable Integer id) {
        CpShop record = cpShopService.parse(ListUtil.getList(cpShopService.getById(id))).get(0);
        model.addAttribute("record",record);
        return "duohui/dianpu/about";
    }

    /**
     * 打开店铺主页
     *
     * @param model
     * @return
     */
    @GetMapping("/product/{id}")
    public String product(Model model,@PathVariable Integer id) {
        Page<CpGoods> goodsPage = cpGoodsService.page(1L, 10L, MapUtil.getMap("shopId", id));
        CpShop record = cpShopService.parse(ListUtil.getList(cpShopService.getById(id))).get(0);
        model.addAttribute("record",record);
        model.addAttribute("goodsList",cpGoodsService.parse(goodsPage.getRecords()));

        return "duohui/dianpu/product";
    }

    /**
     * 打开店铺主页
     *
     * @param model
     * @return
     */
    @GetMapping("/information/{id}")
    public String information(Model model,@PathVariable Integer id) {
        Page<CpGoods> goodsPage = cpGoodsService.page(1L, 10L, MapUtil.getMap("shopId", id));
        CpShop record = cpShopService.parse(ListUtil.getList(cpShopService.getById(id))).get(0);
        model.addAttribute("record",record);
        model.addAttribute("goodsList",cpGoodsService.parse(goodsPage.getRecords()));

        return "duohui/dianpu/information";
    }

    /**
     * 打开店铺主页
     *
     * @param model
     * @return
     */
    @GetMapping("/inforcon/{id}")
    public String inforcon(Model model,@PathVariable Integer id) {
        Page<CpGoods> goodsPage = cpGoodsService.page(1L, 10L, MapUtil.getMap("shopId", id));
        CpShop record = cpShopService.parse(ListUtil.getList(cpShopService.getById(id))).get(0);
        model.addAttribute("record",record);
        model.addAttribute("goodsList",cpGoodsService.parse(goodsPage.getRecords()));

        return "duohui/dianpu/inforcon";
    }

    /**
     * 打开店铺主页
     *
     * @param model
     * @return
     */
    @GetMapping("/special/{id}")
    public String special(Model model,@PathVariable Integer id) {
        Page<CpGoods> goodsPage = cpGoodsService.page(1L, 10L, MapUtil.getMap("shopId", id));
        CpShop record = cpShopService.parse(ListUtil.getList(cpShopService.getById(id))).get(0);
        model.addAttribute("record",record);
        model.addAttribute("goodsList",cpGoodsService.parse(goodsPage.getRecords()));

        return "duohui/dianpu/special";
    }

    /**
     * 打开店铺主页
     *
     * @param model
     * @return
     */
    @GetMapping("/contact/{id}")
    public String contact(Model model,@PathVariable Integer id) {
        Page<CpGoods> goodsPage = cpGoodsService.page(1L, 10L, MapUtil.getMap("shopId", id));
        CpShop record = cpShopService.parse(ListUtil.getList(cpShopService.getById(id))).get(0);
        model.addAttribute("record",record);
        model.addAttribute("goodsList",cpGoodsService.parse(goodsPage.getRecords()));

        return "duohui/dianpu/contact";
    }

}
