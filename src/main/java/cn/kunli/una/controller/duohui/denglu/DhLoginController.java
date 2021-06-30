package cn.kunli.una.controller.duohui.denglu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/duohui/login")
public class DhLoginController {
    @GetMapping("/toLogin")
    public String toLogin() {
        return "duohui/denglu/login";
    }
}
