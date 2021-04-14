package cn.kunli.una.controller.duohui.gongqiu;

import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/duohui/gongqiu")
public class GqIndexController {
    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    SysConfigurationService sysConfigurationService;

    /**
     * 打开主体框架
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        /*SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        List<SysMenu> sysMenuList = sysMenuService.selectTreeBySelective((SysMenu) new SysMenu().setPlatformDname("platform_pc").setId(loginUser.getId()));
        SysConfiguration sysConfiguration = sysConfigurationService.queryFromRedis("systemTitle");
        model.addAttribute("sysMenuList", sysMenuList);
        model.addAttribute("systemName", sysConfiguration.getValue());
        model.addAttribute("activeUser", loginUser);*/
        return "duohui/gongqiu/index";
    }

    /**
     * 跳转主页
     *
     * @param model
     * @return
     */
    @RequestMapping("/homepage")
    public String homepage(Model model) {
        return "index/homepage";
    }

    //未授权页面
    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "error/401";
    }
}
