package cn.kunli.una.controller.duohui.toubiao;

import cn.kunli.una.pojo.bid.BidProject;
import cn.kunli.una.pojo.sys.*;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.toubiao.BidProjectService;
import cn.kunli.una.service.sys.*;
import cn.kunli.una.utils.common.UnaListUtil;
import cn.kunli.una.utils.common.UnaMapUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/duohui/toubiao")
public class BidIndexController {
    @Autowired
    private RedisUtil<Integer> redisUtil;
    @Autowired
    private BidProjectService bidProjectService;
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    SysConfigurationService sysConfigurationService;
    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private SysDataService sysDataService;
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
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String requestedSessionId = request.getRequestedSessionId();
        if(regionId!=null){
            redisUtil.set("regionId:"+request.getRequestedSessionId(),regionId,3600*12);
        }
        boolean b = redisUtil.hasKey("regionId:" + requestedSessionId);
        if(b){
            regionId = redisUtil.get("regionId:" + requestedSessionId);
        }*/
        getCommonItem(model);
        //行业字典
        List<SysDictionary> industryDlist = sysDictionaryService.parse(sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", "industry")));
        //按行业分页查询
        Map<String,Object> projectListMap = new HashMap<>();
        //查询实例
        Map<String, Object> paramMap = UnaMapUtil.getMap("statusDcode", "dh_bidStatus_applying");

        for (SysDictionary primaryIndustryDic : industryDlist) {
            if(CollectionUtils.isNotEmpty(primaryIndustryDic.getChildren())){
                for (SysDictionary secondryIndustryDic : primaryIndustryDic.getChildren()) {
                    paramMap.put(":industryDcodes",","+secondryIndustryDic.getCode());
                    Page<BidProject> page = bidProjectService.page(0, 12, paramMap);
                    projectListMap.put(secondryIndustryDic.getCode(),bidProjectService.parse(page.getRecords()));
                }
            }
        }

        model.addAttribute("industryDlist",industryDlist);
        model.addAttribute("projectListMap",projectListMap);

        Map<String, Object> projectParamMap = UnaMapUtil.getMap("orderByDesc", "createTime");
        //已中标项目
        projectParamMap.put("statusDcode", "dh_bidStatus_publicity");
        Page<BidProject> publicityPage = bidProjectService.page(0, 10, projectParamMap);
        model.addAttribute("publicityProjectList",bidProjectService.parse(publicityPage.getRecords()));
        //拟在建项目
        projectParamMap.put("statusDcode", "dh_bidStatus_notStart");
        Page<BidProject> notStartPage = bidProjectService.page(0, 10, projectParamMap);
        model.addAttribute("notStartProjectList",bidProjectService.parse(notStartPage.getRecords()));
        //报名中项目
        projectParamMap.put("statusDcode", "dh_bidStatus_applying");
        Page<BidProject> applyingPage = bidProjectService.page(0, 10, projectParamMap);
        model.addAttribute("applyingProjectList",bidProjectService.parse(applyingPage.getRecords()));

        //合作企业
        Page<SysCompany> coopShopPage = sysCompanyService.page(1L,20L, UnaMapUtil.buildHashMap().put("orderByDesc", "refreshTime").build());
        List<SysCompany> coopShopList = sysCompanyService.parse(coopShopPage.getRecords());
        model.addAttribute("coopShopList",coopShopList);

        //首页信息
        SysData record = sysDataService.getById(100024);
        record = sysDataService.parse(UnaListUtil.getList(record)).get(0);
        model.addAttribute("record",record);

        return "duohui/toubiao/index";
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

        //项目状态
        List<SysDictionary> bidStatusDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", "dh_bidStatus"));
        model.addAttribute("bidStatusDlist",bidStatusDlist);

        //行业字典
        List<SysDictionary> primaryIndustryDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", "industry"));
        model.addAttribute("primaryIndustryDlist",primaryIndustryDlist);
        if(map.containsKey("primaryIndustryDcode")){
            List<SysDictionary> secondryIndustryDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", map.get("primaryIndustryDcode")));
            model.addAttribute("secondryIndustryDlist",secondryIndustryDlist);
            map.put(":industryDcodes",map.get("primaryIndustryDcode")+",");
            map.remove("primaryIndustryDcode");

            if(map.containsKey("secondryIndustryDcode")){
                List<SysDictionary> thirdryIndustryDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", map.get("secondryIndustryDcode")));
                model.addAttribute("thirdryIndustryDlist",thirdryIndustryDlist);
                map.put(":industryDcodes",map.get(":industryDcodes").toString()+map.get("secondryIndustryDcode")+",");
                map.remove("secondryIndustryDcode");

                if(map.containsKey("thirdryIndustryDcode")){
                    map.put(":industryDcodes",map.get(":industryDcodes").toString()+map.get("thirdryIndustryDcode"));
                    map.remove("thirdryIndustryDcode");
                }
            }
        }

        Page<BidProject> projectPage = bidProjectService.page(map.get("pageNum"), 16, map);
        model.addAttribute("projectList",bidProjectService.parse(projectPage.getRecords()));

        //省级地区
        List<SysRegion> sysRegionList = sysRegionService.selectList(UnaMapUtil.getMap("level", 2));
        model.addAttribute("sysRegionList",sysRegionList);

        return "duohui/toubiao/list";
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
        SysConfiguration systemTitle = sysConfigurationService.selectOne(UnaMapUtil.getMap("code","systemTitle"));
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

        //搜索排行
        /*Page<CpGoods> searchPage = cpGoodsService.page(1L,10L, MapUtil.getMap("orderByDesc", "viewAmount"));
        model.addAttribute("searchGoodsList",searchPage.getRecords());*/

        //点击排行
        Page<BidProject> browsePage = bidProjectService.page(0, 10, UnaMapUtil.getMap("orderByDesc", "browseCount"));
        model.addAttribute("browseProjectList",browsePage.getRecords());
    }
}
