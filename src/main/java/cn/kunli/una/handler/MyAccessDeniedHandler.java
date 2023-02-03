package cn.kunli.una.handler;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.utils.common.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    /*private String url;

    public MyAccessDeniedHandler(String url) {
        this.url = url;
    }*/

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //响应状态
        ResponseUtil.responseJson(httpServletResponse,HttpServletResponse.SC_OK, SysResult.fail("操作失败：权限不足"));
    }
}
