package cn.kunli.una.controller.duohui.chanpin;

import cn.kunli.una.pojo.duohui.guanwang.GwConfiguration;
import cn.kunli.una.pojo.duohui.guanwang.GwMenu;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.pojo.system.SysMenu;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysMenuService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/duohui/chanpin")
public class CpIndexController {
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
        getCommonItem(model);
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
    }
}
