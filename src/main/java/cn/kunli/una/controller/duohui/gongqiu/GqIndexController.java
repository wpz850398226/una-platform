package cn.kunli.una.controller.duohui.gongqiu;

import cn.kunli.una.pojo.chanpin.CpGoods;

import cn.kunli.una.pojo.gongqiu.GqInformation;
import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysRegion;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;

import cn.kunli.una.service.duohui.gongqiu.GqInformationService;
import cn.kunli.una.service.system.SysCompanyService;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysRegionService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private SysCompanyService sysCompanyService;
    @Autowired
    private SysRegionService sysRegionService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        getCommonItem(model);

        //行业字典
        List<SysDictionary> industryDlist = sysDictionaryService.parse(sysDictionaryService.selectList(MapUtil.getMap("parentCode", "industry")));
        model.addAttribute("industryDlist",industryDlist);

        //查询供求信息
        Map<String,List<GqInformation>> gqInformationListMap = new HashMap<>();
        if(CollectionUtils.isNotEmpty(industryDlist)){
            for (SysDictionary primaryIndustry : industryDlist) {
                if(CollectionUtils.isNotEmpty(primaryIndustry.getChildren())){
                    for (SysDictionary secondryIndustry : primaryIndustry.getChildren()) {
                        Page<GqInformation> informationPage = gqInformationService.page(1L, 5L, MapUtil.buildHashMap()
                                .put("secondryIndustryDcode", secondryIndustry.getCode()).put("isAdded",true).put("isAudit",true).build());
                        gqInformationListMap.put(secondryIndustry.getCode(),informationPage.getRecords());
                    }
                }
            }
        }
        model.addAttribute("gqInformationListMap",gqInformationListMap);

        //精品推荐
        Page<GqInformation> hotPage = gqInformationService.page(1L, 8L, MapUtil.getMap("isHot", true));
        model.addAttribute("hotList",gqInformationService.parse(hotPage.getRecords()));


        //合作企业
        Page<SysCompany> coopShopPage = sysCompanyService.page(1L,20L, MapUtil.buildHashMap().put("orderByDesc", "refreshTime").build());
        List<SysCompany> parse = sysCompanyService.parse(coopShopPage.getRecords());
        List<SysCompany> coopShopList = new ArrayList<>();
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

    /**
     * 列表页
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model,@RequestParam Map<String,Object> map) {
        model.addAttribute("param", map);
        getCommonItem(model);

        //行业字典
        List<SysDictionary> primaryIndustryDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", "industry"));
        model.addAttribute("primaryIndustryDlist",primaryIndustryDlist);

        if(map.containsKey("primaryIndustryDcode")){
            List<SysDictionary> secondryIndustryDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", map.get("primaryIndustryDcode")));
            model.addAttribute("secondryIndustryDlist",secondryIndustryDlist);
        }

        if(map.containsKey("secondryIndustryDcode")){
            List<SysDictionary> thirdryIndustryDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", map.get("secondryIndustryDcode")));
            model.addAttribute("thirdryIndustryDlist",thirdryIndustryDlist);
        }

        //搜索结果
        Long pageNum = 1L;
        Long pageSize = 16L;
        if(map==null){
            map = new HashMap<>();
        }else{
            if(map.containsKey("pageNum")){
                pageNum = Long.valueOf(map.get("pageNum").toString());
                map.remove("pageNum");
            }
            if(map.containsKey("pageSize")){
                pageSize = Long.valueOf(map.get("pageSize").toString());
                map.remove("pageSize");
            }
        }
        map.put("orderByDesc", "stickDeadline");
        Page<GqInformation> informationPage = gqInformationService.page(pageNum,pageSize, map);
        model.addAttribute("recordList",gqInformationService.parse(informationPage.getRecords()));

        //认证情况
        List<SysDictionary> certifyTypeDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", "dh_certify"));
        model.addAttribute("certifyTypeDlist",certifyTypeDlist);

        //信息类型
        List<SysDictionary> informationTypeDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", "dh_informationType"));
        model.addAttribute("informationTypeDlist",informationTypeDlist);

        //来源类型
        List<SysDictionary> originTypeDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", "dh_originType"));
        model.addAttribute("originTypeDlist",originTypeDlist);

        //省级地区
        List<SysRegion> sysRegionList = sysRegionService.selectList(MapUtil.getMap("level", 2));
        model.addAttribute("sysRegionList",sysRegionList);

        return "duohui/gongqiu/gongqiu";
    }

    public void getCommonItem(Model model){
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        SysConfiguration systemTitle = sysConfigurationService.selectOne(MapUtil.getMap("code","systemTitle"));
        model.addAttribute("systemName", systemTitle);
        model.addAttribute("activeUser", loginUser);

        //热卖推荐
        Page<CpGoods> stickGoodsPage = cpGoodsService.page(1L,3L, MapUtil.getMap("orderByDesc", "stickDeadline"));
        model.addAttribute("hotGoodsList",cpGoodsService.parse(stickGoodsPage.getRecords()));

        //搜索排行
        Page<GqInformation> searchPage = gqInformationService.page(1L,10L, MapUtil.getMap("orderByDesc", "viewAmount"));
        model.addAttribute("searchList",searchPage.getRecords());
    }
}
