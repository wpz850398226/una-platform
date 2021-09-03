package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.system.*;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.system.*;
import cn.kunli.una.utils.common.DateUtil;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/duohui/chanpin")
public class CpIndexController {
    @Autowired
    private RedisUtil<Integer> redisUtil;
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
    private SysCompanyService sysCompanyService;
    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private SysAnnouncementService sysAnnouncementService;

    /**
     * 打开主页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model,Integer regionId) {
        //查询对话所属地区
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String requestedSessionId = request.getRequestedSessionId();
        if(regionId!=null){
            redisUtil.set("regionId:"+request.getRequestedSessionId(),regionId,3600*12);
        }
        boolean b = redisUtil.hasKey("regionId:" + requestedSessionId);
        if(b){
            regionId = redisUtil.get("regionId:" + requestedSessionId);
        }
        getCommonItem(model);
        //行业字典
        List<SysDictionary> industryDlist = sysDictionaryService.parse(sysDictionaryService.selectList(MapUtil.getMap("parentCode", "industry")));
        model.addAttribute("industryDlist",industryDlist);
        //首页信息
        SysData record = sysDataService.getById(100017);
        record = sysDataService.parse(ListUtil.getList(record)).get(0);
        //热门商铺
        if(record.getValue().get("remaiCompanyIds")!=null){
            String remaiCompanyIds = String.valueOf(record.getValue().get("remaiCompanyIds"));
            List<SysCompany> list = sysCompanyService.parse(sysCompanyService.selectList(MapUtil.getMap("in:id", remaiCompanyIds)));
            model.addAttribute("hotShopList",list);
        }

        model.addAttribute("record",record);

        List<SysDictionary> goodsStatusDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", "dh_goodsStatus"));
        if(CollectionUtils.isNotEmpty(goodsStatusDlist)){
            //按商品状态查询商品列表
            Map<String,Object> goodsListMap = new HashMap<>();
            Map<String, Object> goodsParamMap = MapUtil.buildHashMap().put("isAdded", true).put("isAudit", true).build();
            if(regionId!=null)goodsParamMap.put(":regionIds",regionId+",");
            for (SysDictionary sysDictionary : goodsStatusDlist) {
                goodsParamMap.put("statusDcode", sysDictionary.getCode());
                Page<CpGoods> goodsPage = cpGoodsService.page(1L,4L, goodsParamMap);
                goodsListMap.put(sysDictionary.getCode(),cpGoodsService.parse(goodsPage.getRecords()));
            }
            //商品状态字典
            model.addAttribute("goodsStatusDlist",goodsStatusDlist);
            model.addAttribute("goodsListMap",goodsListMap);

            //查询置顶商铺
            List<SysCompany> companyList = new ArrayList<>();
            Page<SysCompany> stickShopPage = sysCompanyService.page(1L,Long.valueOf(goodsStatusDlist.size()), MapUtil.buildHashMap().put("ge:stickDeadline", new Date()).put("orderByDesc", "stickDeadline").build());
            companyList = stickShopPage.getRecords();
            if(companyList.size()<=goodsStatusDlist.size()){
                Page<SysCompany> refreshShopPage = sysCompanyService.page(1L,Long.valueOf(goodsStatusDlist.size()), MapUtil.buildHashMap().put("le:stickDeadline", new Date()).put("orderByDesc", "refreshTime").build());
                if(refreshShopPage.getTotal()>0){
                    List<SysCompany> records = refreshShopPage.getRecords();
                    if(companyList.size()==0){
                        companyList = records;
                    }else{
                        companyList.addAll(records);
                    }
                }
            }

            model.addAttribute("recommendShopList",sysCompanyService.parse(companyList));

            //合作企业
            Page<SysCompany> coopShopPage = sysCompanyService.page(1L,20L, MapUtil.buildHashMap().put("orderByDesc", "refreshTime").build());
            List<SysCompany> coopShopList = sysCompanyService.parse(coopShopPage.getRecords());
            model.addAttribute("coopShopList",coopShopList);

        }

        //查询公告
        String strOfDate = DateUtil.getStrOfDate(new Date(), "yyyy-MM-dd");
        Page<SysAnnouncement> sysAnnouncementPage = sysAnnouncementService.page(1L, 4L, MapUtil.buildHashMap()
                .put(":platformDcode", "platform_type_chanpin").put("le:startTime",strOfDate)
                .put("ge:endTime",strOfDate).build());
        model.addAttribute("sysAnnouncementList",sysAnnouncementPage.getRecords());

        return "duohui/chanpin/index";
    }

    /**
     * 打开搜索页
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
            map.put(":industryTypeDcodes",map.get("primaryIndustryDcode")+",");
            map.remove("primaryIndustryDcode");

            if(map.containsKey("secondryIndustryDcode")){
                List<SysDictionary> thirdryIndustryDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", map.get("secondryIndustryDcode")));
                model.addAttribute("thirdryIndustryDlist",thirdryIndustryDlist);
                map.put(":industryTypeDcodes",map.get(":industryTypeDcodes").toString()+map.get("secondryIndustryDcode")+",");
                map.remove("secondryIndustryDcode");

                if(map.containsKey("thirdryIndustryDcode")){
                    map.put(":industryTypeDcodes",map.get(":industryTypeDcodes").toString()+map.get("thirdryIndustryDcode"));
                    map.remove("thirdryIndustryDcode");
                }
            }
        }

        map.put("orderByDesc", "stickDeadline");
        Page<CpGoods> goodsPage = cpGoodsService.page(map.get("pageNum"),16, map);
        model.addAttribute("goodsList",cpGoodsService.parse(goodsPage.getRecords()));

        //商品状态
        List<SysDictionary> goodsStatusDlist = sysDictionaryService.selectList(MapUtil.getMap("parentCode", "dh_goodsStatus"));
        model.addAttribute("goodsStatusDlist",goodsStatusDlist);

        //省级地区
        List<SysRegion> sysRegionList = sysRegionService.selectList(MapUtil.getMap("level", 2));
        model.addAttribute("sysRegionList",sysRegionList);

        return "duohui/chanpin/product";
    }

    /**
     * 打开新闻页
     * @param model
     * @return
     */
    @RequestMapping("/newsview/{id}")
    public String newsview(Model model,@PathVariable Integer id) {
        getCommonItem(model);
        SysAnnouncement announcement = sysAnnouncementService.getById(id);
        model.addAttribute("record",announcement);

        return "duohui/chanpin/newsview";
    }

    public void getCommonItem(Model model){
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        SysConfiguration systemTitle = sysConfigurationService.selectOne(MapUtil.getMap("code","systemTitle"));
        model.addAttribute("systemName", systemTitle);
        model.addAttribute("activeUser", loginUser);

        //查询对话所属地区
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String requestedSessionId = request.getRequestedSessionId();
        boolean b = redisUtil.hasKey("regionId:" + requestedSessionId);
        if(b){
            Integer regionId = redisUtil.get("regionId:" + requestedSessionId);
            SysRegion sysRegion = sysRegionService.getById(regionId);
            model.addAttribute("sysRegion",sysRegion);
        }

        //热卖推荐
        Page<CpGoods> stickGoodsPage = cpGoodsService.page(1L,3L, MapUtil.getMap("orderByDesc", "stickDeadline"));
        model.addAttribute("hotGoodsList",cpGoodsService.parse(stickGoodsPage.getRecords()));

        //搜索排行
        Page<CpGoods> searchPage = cpGoodsService.page(1L,10L, MapUtil.getMap("orderByDesc", "viewAmount"));
        model.addAttribute("searchGoodsList",searchPage.getRecords());
    }
}
