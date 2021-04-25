package cn.kunli.una.controller.duohui.guanwang;

import cn.kunli.una.pojo.duohui.guanwang.GwConfiguration;
import cn.kunli.una.pojo.duohui.guanwang.GwMenu;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysMenu;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysParamMap;
import cn.kunli.una.service.duohui.guanwang.GwConfigurationService;
import cn.kunli.una.service.duohui.guanwang.GwMenuService;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysMenuService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/duohui/guanwang")
public class GwIndexController {
    @Autowired
    GwMenuService gwMenuService;
    @Autowired
    GwConfigurationService gwConfigurationService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        List<GwMenu> gwMenuList = gwMenuService.list(gwMenuService.getWrapper(MapUtil.getMap("parentId", 100000)));
        GwConfiguration systemTitle = gwConfigurationService.queryFromRedis("systemTitle");
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
