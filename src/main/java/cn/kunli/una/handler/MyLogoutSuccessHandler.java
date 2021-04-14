package cn.kunli.una.handler;

import cn.kunli.una.filter.TokenFilter;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.security.TokenService;
import cn.kunli.una.utils.common.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private TokenFilter tokenFilter;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = tokenFilter.getToken(request);
        tokenService.deleteToken(token);

        ResponseUtil.responseJson(response,HttpStatus.OK.value(), SysResult.success("退出成功"));

    }
}
