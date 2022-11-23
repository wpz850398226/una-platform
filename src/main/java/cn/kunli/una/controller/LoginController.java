package cn.kunli.una.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = "系统-登录")
@RequestMapping("/sys/login")
public class LoginController {
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login/login";
    }
}
