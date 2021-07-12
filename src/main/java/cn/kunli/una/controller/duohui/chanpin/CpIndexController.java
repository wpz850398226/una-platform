package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysData;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysDataService;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysMenuService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/duohui/chanpin")
public class CpIndexController {
    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    SysConfigurationService sysConfigurationService;
    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private CpGoodsService cpGoodsService;
    @Autowired
    private SysDataService sysDataService;
    @Autowired
    private CpShopService cpShopService;

    /**
     * 打开主页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        getCommonItem(model);
        SysData record = sysDataService.getById(100017);
        record = sysDataService.parse(ListUtil.getList(record)).get(0);
        //热门商铺
        if(record.getValue().get("rmsp")!=null){
            String rmsp = String.valueOf(record.getValue().get("rmsp"));
            List<CpShop> list = cpShopService.parse(cpShopService.selectList(MapUtil.getMap("in:code", rmsp)));
            model.addAttribute("hotShopList",list);
        }

        model.addAttribute("record",record);

        SysDictionary goodsStatusDic = sysDictionaryService.selectOne(sysDictionaryService.format(MapUtil.getMap("code", "dh_goodsStatus")));
        if(goodsStatusDic!=null){
            List<SysDictionary> goodsStatusDlist = sysDictionaryService.selectList(MapUtil.getMap("parentId", goodsStatusDic.getId()));
            if(CollectionUtils.isNotEmpty(goodsStatusDlist)){
                //按商品状态查询商品列表
                Map<String,Object> goodsListMap = new HashMap<>();
                for (SysDictionary sysDictionary : goodsStatusDlist) {
                    Page<CpGoods> goodsPage = cpGoodsService.page(1L,4L, MapUtil.getMap("statusDcode", sysDictionary.getCode()));
                    goodsListMap.put(sysDictionary.getCode(),cpGoodsService.parse(goodsPage.getRecords()));
                }
                //商品状态字典
                model.addAttribute("goodsStatusDlist",goodsStatusDlist);
                model.addAttribute("goodsListMap",goodsListMap);

                //查询置顶商铺
                List<CpShop> shopList = new ArrayList<>();
                Page<CpShop> stickShopPage = cpShopService.page(1L,Long.valueOf(goodsStatusDlist.size()), MapUtil.buildHashMap().put("le:stickDeadline", new Date()).put("orderByDesc", "stickDeadline").build());
                shopList = stickShopPage.getRecords();
                if(shopList.size()<=goodsStatusDlist.size()){
                    Page<CpShop> refreshShopPage = cpShopService.page(1L,Long.valueOf(goodsStatusDlist.size()), MapUtil.buildHashMap().put("ge:stickDeadline", new Date()).put("orderByDesc", "refreshTime").build());
                    shopList.addAll(refreshShopPage.getRecords());
                }

                model.addAttribute("recommendShopList",cpShopService.parse(shopList));

                Page<CpShop> coopShopPage = cpShopService.page(1L,20L, MapUtil.buildHashMap().put("orderByDesc", "refreshTime").build());
                model.addAttribute("coopShopList",cpShopService.parse(coopShopPage.getRecords()));

            }
        }

        return "duohui/chanpin/index";
    }

    /**
     * 打开搜索页
     * @param model
     * @return
     */
    @RequestMapping("/product")
    public String product(Model model,Map<String,Object> map) {
        getCommonItem(model);
        Page<CpGoods> goodsPage = cpGoodsService.page(1L,8L, MapUtil.buildHashMap().put("orderByDesc", "stickDeadline").build());
        model.addAttribute("goodsList",cpGoodsService.parse(goodsPage.getRecords()));
        return "duohui/chanpin/product";
    }

    public void getCommonItem(Model model){
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        SysConfiguration systemTitle = sysConfigurationService.selectOne(MapUtil.getMap("code","systemTitle"));
        model.addAttribute("systemName", systemTitle);
        model.addAttribute("activeUser", loginUser);

        //行业字典
        SysDictionary industryDictionary = sysDictionaryService.selectOne(MapUtil.getMap("code", "industry"));
        if(industryDictionary!=null){
            List<SysDictionary> industryDlist = sysDictionaryService.parse(sysDictionaryService.selectList(MapUtil.getMap("parentId", industryDictionary.getId())));
            model.addAttribute("industryDlist",industryDlist);
        }

        //热卖推荐
        Page<CpGoods> stickGoodsPage = cpGoodsService.page(1L,3L, MapUtil.getMap("orderByDesc", "stickDeadline"));
        model.addAttribute("hotGoodsList",cpGoodsService.parse(stickGoodsPage.getRecords()));

        //搜索排行
        Page<CpGoods> searchGoodsPage = cpGoodsService.page(1L,10L, MapUtil.getMap("orderByDesc", "viewAmount"));
        model.addAttribute("searchGoodsList",searchGoodsPage.getRecords());
    }
}
