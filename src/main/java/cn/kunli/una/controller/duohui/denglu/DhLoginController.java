package cn.kunli.una.controller.duohui.denglu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/duohui/login")
public class DhLoginController {
    @GetMapping("/toLogin")
    public String toLogin(Model model,HttpServletRequest req) {
        String requestURI = "/api/duohui/chanpin/index";

        Object obj = req.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if(obj!=null){
            DefaultSavedRequest spring_security_saved_request = (DefaultSavedRequest)obj;
            requestURI = spring_security_saved_request.getRequestURI();
        }

        model.addAttribute("originUri",requestURI);
        return "duohui/denglu/login";
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "duohui/denglu/register";
    }
}
