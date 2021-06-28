package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.chanpin.CpGoods;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysMenuService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        getCommonItem(model);

        SysDictionary goodsStatusDic = sysDictionaryService.getOne(sysDictionaryService.getWrapper(sysDictionaryService.format(MapUtil.getMap("code", "dh_goodsStatus"))));
        if(goodsStatusDic!=null){
            List<SysDictionary> goodsStatusDlist = sysDictionaryService.list(sysDictionaryService.getWrapper(sysDictionaryService.format(MapUtil.getMap("parentId", goodsStatusDic.getId()))));
            if(CollectionUtils.isNotEmpty(goodsStatusDlist)){
                //按商品状态查询商品列表
                Page<CpGoods> objectPage = new Page<CpGoods>().setCurrent(1).setSize(4);
                Map<String,Object> goodsListMap = new HashMap<>();
                for (SysDictionary sysDictionary : goodsStatusDlist) {
                    Page<CpGoods> goodsPage = cpGoodsService.page(objectPage, cpGoodsService.getWrapper(MapUtil.getMap("statusDcode", sysDictionary.getCode())));
                    goodsListMap.put(sysDictionary.getCode(),goodsPage.getRecords());
                }
                //商品状态字典
                model.addAttribute("goodsStatusDlist",goodsStatusDlist);
                model.addAttribute("goodsListMap",goodsListMap);
            }
        }

        return "duohui/chanpin/index";
    }

    //未授权页面
    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "error/401";
    }

    private void getCommonItem(Model model){
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        SysConfiguration systemTitle = sysConfigurationService.getOne(sysConfigurationService.getWrapper(MapUtil.getMap("code","systemTitle")));
        model.addAttribute("systemName", systemTitle);
        model.addAttribute("activeUser", loginUser);

        //行业字典
        SysDictionary industryDictionary = sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code", "industry")));
        if(industryDictionary!=null){
            List<SysDictionary> industryDlist = sysDictionaryService.parse(sysDictionaryService.list(sysDictionaryService.getWrapper(MapUtil.getMap("parentId", industryDictionary.getId()))));
            model.addAttribute("industryDlist",industryDlist);
        }
    }
}
