package cn.kunli.una.controller.duohui.qiye;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.chanpin.CpShop;
import cn.kunli.una.pojo.system.*;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.duohui.chanpin.CpShopService;
import cn.kunli.una.service.system.*;
import cn.kunli.una.utils.common.DateUtil;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/duohui/qiye")
public class QyIndexController {
    @Autowired
    private SysCompanyService sysCompanyService;
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
    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private SysAnnouncementService sysAnnouncementService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        //行业字典
        List<SysDictionary> industryDlist = sysDictionaryService.parse(sysDictionaryService.selectList(MapUtil.getMap("parentCode", "industry")));
        model.addAttribute("industryDlist",industryDlist);
        //首页信息
        SysData record = sysDataService.getById(100017);
        record = sysDataService.parse(ListUtil.getList(record)).get(0);
        //热门商铺
        if(record.getValue().get("rmsp")!=null){
            String rmsp = String.valueOf(record.getValue().get("rmsp"));
            List<CpShop> list = cpShopService.parse(cpShopService.selectList(MapUtil.getMap("in:code", rmsp)));
            model.addAttribute("hotShopList",list);
        }

        model.addAttribute("record",record);

        //查询公告
        String strOfDate = DateUtil.getStrOfDate(new Date(), "yyyy-MM-dd");
        Page<SysAnnouncement> sysAnnouncementPage = sysAnnouncementService.page(1L, 4L, MapUtil.buildHashMap()
                .put(":platformDcode", "platform_type_chanpin").put("le:startTime",strOfDate)
                .put("ge:endTime",strOfDate).build());
        model.addAttribute("sysAnnouncementList",sysAnnouncementPage.getRecords());

        Map<String,List<SysCompany>> companyListMap = new HashMap<>();
        for (int i = 0; i < industryDlist.size(); i++) {
            if(i<6){
                String code = industryDlist.get(i).getCode();
                Page<SysCompany> companyPage = sysCompanyService.page(1L, 4L, MapUtil.getMap("primaryIndustryDcode", code));
                List<SysCompany> records = sysCompanyService.parse(companyPage.getRecords());
                companyListMap.put(code,records);
            }
        }

        model.addAttribute("companyListMap",companyListMap);

        return "duohui/qiye/index";
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
        Page<CpGoods> searchPage = cpGoodsService.page(1L,10L, MapUtil.getMap("orderByDesc", "viewAmount"));
        model.addAttribute("searchGoodsList",searchPage.getRecords());
    }
}
