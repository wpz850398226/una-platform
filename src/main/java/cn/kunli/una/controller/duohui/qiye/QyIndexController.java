package cn.kunli.una.controller.duohui.qiye;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.sys.*;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.sys.*;
import cn.kunli.una.utils.common.DateUtil;
import cn.kunli.una.utils.common.UnaListUtil;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@Api(tags = "企业-主页")
@RequestMapping("/duohui/qiye")
public class QyIndexController {

    @Autowired
    SysConfigurationService sysConfigurationService;
    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private CpGoodsService cpGoodsService;
    @Autowired
    private SysDataService sysDataService;
    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private SysAnnouncementService sysAnnouncementService;
    @Autowired
    private SysCompanyService sysCompanyService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        //行业字典
        List<SysDictionary> industryDlist = sysDictionaryService.parse(sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", "industry")));
        model.addAttribute("industryDlist",industryDlist);
        //首页信息
        SysData record = sysDataService.getById(100017);
        record = sysDataService.parse(UnaListUtil.getList(record)).get(0);
        //热门商铺
        if(record.getValue().get("remaiCompanyIds")!=null){
            String remaiCompanyIds = String.valueOf(record.getValue().get("remaiCompanyIds"));
            List<SysCompany> list = sysCompanyService.parse(sysCompanyService.selectList(UnaMapUtil.getMap("in:id", remaiCompanyIds)));
            model.addAttribute("hotShopList",list);
        }

        model.addAttribute("record",record);

        //查询公告
        String strOfDate = DateUtil.getStrOfDate(new Date(), "yyyy-MM-dd");
        Page<SysAnnouncement> sysAnnouncementPage = sysAnnouncementService.page(1L, 4L, UnaMapUtil.buildHashMap()
                .put(":platformDcode", "platform_type_chanpin").put("le:startTime",strOfDate)
                .put("ge:endTime",strOfDate).build());
        model.addAttribute("sysAnnouncementList",sysAnnouncementPage.getRecords());

        //查询置顶商铺
        List<SysCompany> companyList = new ArrayList<>();
        Page<SysCompany> stickShopPage = sysCompanyService.page(1L,6L, UnaMapUtil.buildHashMap().put("ge:stickDeadline", new Date()).put("orderByDesc", "stickDeadline").build());
        companyList = stickShopPage.getRecords();
        if(companyList.size()<=6){
            Page<SysCompany> refreshShopPage = sysCompanyService.page(1L,6L, UnaMapUtil.buildHashMap().put("le:stickDeadline", new Date()).put("orderByDesc", "refreshTime").build());
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

        Map<String,List<SysCompany>> companyListMap = new HashMap<>();
        for (int i = 0; i < industryDlist.size(); i++) {
            if(i<6){
                String code = industryDlist.get(i).getCode();
                Page<SysCompany> companyPage = sysCompanyService.page(1L, 4L, UnaMapUtil.getMap("in:industryTypeDcodes", code+","));
                List<SysCompany> records = sysCompanyService.parse(companyPage.getRecords());
                companyListMap.put(code,records);
            }
        }

        model.addAttribute("companyListMap",companyListMap);

        return "duohui/qiye/index";
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
        List<SysDictionary> primaryIndustryDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", "industry"));
        model.addAttribute("primaryIndustryDlist",primaryIndustryDlist);

        if(map.containsKey("primaryIndustryDcode")){
            List<SysDictionary> secondryIndustryDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", map.get("primaryIndustryDcode")));
            model.addAttribute("secondryIndustryDlist",secondryIndustryDlist);
        }

        if(map.containsKey("secondryIndustryDcode")){
            List<SysDictionary> thirdryIndustryDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", map.get("secondryIndustryDcode")));
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
        Page<SysCompany> page = sysCompanyService.page(pageNum, pageSize, map);
        model.addAttribute("recordList",sysCompanyService.parse(page.getRecords()));

        //认证情况
        List<SysDictionary> certifyTypeDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", "dh_certify"));
        model.addAttribute("certifyTypeDlist",certifyTypeDlist);

        //信息类型
        List<SysDictionary> informationTypeDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", "dh_informationType"));
        model.addAttribute("informationTypeDlist",informationTypeDlist);

        //来源类型
        List<SysDictionary> originTypeDlist = sysDictionaryService.selectList(UnaMapUtil.getMap("parentCode", "dh_originType"));
        model.addAttribute("originTypeDlist",originTypeDlist);

        //省级地区
        List<SysRegion> sysRegionList = sysRegionService.selectList(UnaMapUtil.getMap("level", 2));
        model.addAttribute("sysRegionList",sysRegionList);

        return "duohui/qiye/company";
    }

    /**
     * 列表页
     *
     * @param model
     * @return
     */
    @RequestMapping("/fDetail/{id}")
    public String fDetail(Model model,@RequestParam Map<String,Object> map) {
        model.addAttribute("param", map);
        getCommonItem(model);



        return "duohui/qiye/qiyecon";
    }

    public void getCommonItem(Model model){
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        SysConfiguration systemTitle = sysConfigurationService.selectOne(UnaMapUtil.getMap("code","systemTitle"));
        model.addAttribute("systemName", systemTitle);
        model.addAttribute("activeUser", loginUser);

        //热卖推荐
        Page<CpGoods> stickGoodsPage = cpGoodsService.page(1L,3L, UnaMapUtil.getMap("orderByDesc", "stickDeadline"));
        model.addAttribute("hotGoodsList",cpGoodsService.parse(stickGoodsPage.getRecords()));

        //搜索排行
        Page<CpGoods> searchPage = cpGoodsService.page(1L,10L, UnaMapUtil.getMap("orderByDesc", "viewAmount"));
        model.addAttribute("searchGoodsList",searchPage.getRecords());
    }
}
