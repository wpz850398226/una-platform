package cn.kunli.una.controller;

import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysMenu;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysMenuService;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sys/index")
public class IndexController {
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
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();

        List<SysMenu> sysMenuList = sysMenuService.selectByAccount();
        SysConfiguration sysConfiguration = sysConfigurationService.selectOne(UnaMapUtil.getMap("code","systemTitle"));
        model.addAttribute("sysMenuList", sysMenuList);
        //model.addAttribute("systemName", sysConfiguration.getValue());
        model.addAttribute("activeUser", loginUser);
        return "index/index";
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
