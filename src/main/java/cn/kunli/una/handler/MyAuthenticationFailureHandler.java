package cn.kunli.una.handler;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.utils.common.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        String msg = null;
        if(e instanceof BadCredentialsException){
            msg = "密码错误";
            ResponseUtil.responseJson(response, HttpStatus.OK.value(), SysResult.fail(msg));
        }else {
            msg = e.getMessage();
            ResponseUtil.responseJson(response, HttpStatus.OK.value(), SysResult.fail(msg));
        }
    }
}
