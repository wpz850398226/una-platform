package cn.kunli.una.handler;

import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.pojo.vo.SysToken;
import cn.kunli.una.service.security.TokenService;
import cn.kunli.una.utils.common.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SysLoginAccountDetails sysLoginAccountDetails = (SysLoginAccountDetails)authentication.getPrincipal();
        SysToken sysToken = tokenService.saveToken(sysLoginAccountDetails);
        ResponseUtil.responseJson(response, HttpStatus.OK.value(), new SysResult().success("登录成功",sysToken.getToken()));
    }
}
