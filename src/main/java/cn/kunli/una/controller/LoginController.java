package cn.kunli.una.controller;

import cn.kunli.una.service.system.SysAccountService;
import cn.kunli.una.utils.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/login")
public class LoginController {
    private static final transient Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysAccountService objService;


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login/login";
    }



}
