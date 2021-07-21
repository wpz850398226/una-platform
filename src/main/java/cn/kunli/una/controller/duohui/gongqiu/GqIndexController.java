package cn.kunli.una.controller.duohui.gongqiu;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.gongqiu.GqInformation;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.service.system.GqInformationService;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysMenuService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/duohui/gongqiu")
public class GqIndexController {
    @Autowired
    SysConfigurationService sysConfigurationService;
    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private CpGoodsService cpGoodsService;
    @Autowired
    private GqInformationService gqInformationService;
    @Autowired
    private CpShopService cpShopService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        getCommonItem(model);
        //合作企业
        Page<CpShop> coopShopPage = cpShopService.page(1L,20L, MapUtil.buildHashMap().put("orderByDesc", "refreshTime").build());
        List<CpShop> parse = cpShopService.parse(coopShopPage.getRecords());
        List<CpShop> coopShopList = new ArrayList<>();
        coopShopList.addAll(parse);
        coopShopList.addAll(parse);
        coopShopList.addAll(parse);
        coopShopList.addAll(parse);
        coopShopList.addAll(parse);
        coopShopList.addAll(parse);
        coopShopList.addAll(parse);
        coopShopList.addAll(parse);
        model.addAttribute("coopShopList",coopShopList);
        return "duohui/gongqiu/index";
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
        Page<GqInformation> searchPage = gqInformationService.page(1L,10L, MapUtil.getMap("orderByDesc", "viewAmount"));
        model.addAttribute("searchList",searchPage.getRecords());
    }
}
