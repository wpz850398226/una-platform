package cn.kunli.una.controller.duohui.denglu;

import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysRegion;
import cn.kunli.una.service.system.SysAccountService;
import cn.kunli.una.service.system.SysRegionService;
import cn.kunli.una.utils.common.MapUtil;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/duohui/login")
public class DhLoginController {

    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private SysAccountService sysAccountService;

    @GetMapping("/toLogin")
    public String toLogin(Model model,HttpServletRequest req,String oldPath) {
        /*String requestURI = "/api/duohui/chanpin/index";

        Object obj = req.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if(obj!=null){
            DefaultSavedRequest spring_security_saved_request = (DefaultSavedRequest)obj;
            requestURI = spring_security_saved_request.getRequestURI();
        }*/

        if(StrUtil.isBlank(oldPath))oldPath = "/api/duohui/chanpin/index";

        model.addAttribute("originUri",oldPath);
        return "duohui/denglu/login";
    }

    @GetMapping("/toLoginMini")
    public String toLoginMini(Model model,HttpServletRequest req) {
        return "duohui/denglu/loginMini";
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "duohui/denglu/register";
    }

    @GetMapping("/seekPass1")
    public String seekPass1() {
        return "duohui/denglu/zhmm";
    }

    @GetMapping("/seekPass2")
    public String seekPass2(Model model,String username) {
        SysAccount sysAccount = sysAccountService.selectOne(MapUtil.getMap("username", username));
        model.addAttribute("username",username);
        return "duohui/denglu/zhmm1";
    }

    @GetMapping("/seekPass3")
    public String seekPass3(Model model,String username) {
        SysAccount sysAccount = sysAccountService.selectOne(MapUtil.getMap("username", username));
        model.addAttribute("sysAccount",sysAccount);
        return "duohui/denglu/zhmm2";
    }

    @GetMapping("/regionList")
    public String regionList(Model model,String targetUrl) {

        List<SysRegion> regionList = sysRegionService.selectList(MapUtil.getMap("level", 2));
        regionList.forEach(r->r.setChildren(sysRegionService.selectList(MapUtil.getMap("parentId", r.getId()))));

        model.addAttribute("regionList",regionList);
        model.addAttribute("targetUrl",targetUrl);
        return "duohui/denglu/diqu";
    }
}
