package cn.kunli.una.controller.duohui.guanwang;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.duohui.guanwang.GwConfiguration;
import cn.kunli.una.pojo.duohui.guanwang.GwMenu;
import cn.kunli.una.pojo.system.SysData;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.service.duohui.guanwang.GwConfigurationService;
import cn.kunli.una.service.duohui.guanwang.GwMenuService;
import cn.kunli.una.service.system.SysDataService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/duohui/guanwang")
public class GwIndexController extends BaseController<SysDataService, SysData> {
    @Autowired
    private GwMenuService gwMenuService;
    @Autowired
    private GwConfigurationService gwConfigurationService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        SysEntity sysEntity = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code", "GwIndex")));
        List<SysData> list = this.list(MapUtil.buildHashMap().put("entityId", sysEntity.getId()).put("last", "limit 1").build());
        if(CollectionUtils.isNotEmpty(list)){
            model.addAttribute("record",list.get(0));
        }
        List<GwMenu> gwMenuList = gwMenuService.list(gwMenuService.getWrapper(MapUtil.getMap("parentId", 100000)));
        GwConfiguration systemTitle = gwConfigurationService.getOne(gwConfigurationService.getWrapper(MapUtil.getMap("code","systemTitle")));
        model.addAttribute("gwMenuList", gwMenuList);
        model.addAttribute("systemName", systemTitle.getValue());
        return "duohui/guanwang/index";
    }


    /**
     * 关于我们
     *
     * @param model
     * @return
     */
    @RequestMapping("/about")
    public String about(Model model) {
        return "duohui/guanwang/about";
    }

    /**
     * 新闻
     *
     * @param model
     * @return
     */
    @RequestMapping("/news")
    public String news(Model model) {
        return "duohui/guanwang/news";
    }

}
