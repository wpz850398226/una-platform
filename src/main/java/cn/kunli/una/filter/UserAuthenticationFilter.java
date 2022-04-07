package cn.kunli.una.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: 重写spring security获取用户信息过滤器，实现接收json传参
 * @author: Ponzio
 * @create: 2021年1月7日09:48:23
 */
@Component
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = this.getBodyParams(request).get(super.SPRING_SECURITY_FORM_PASSWORD_KEY);
        if(StrUtil.isNotBlank(password)){
            return password;
        }
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = this.getBodyParams(request).get(super.SPRING_SECURITY_FORM_USERNAME_KEY);
        if(StrUtil.isNotBlank(username)){
            return username;
        }
        return super.obtainUsername(request);
    }

    /**
     * 获取body参数  body中的参数只能获取一次
     * @param request
     * @return
     */
    private Map<String,String> getBodyParams(HttpServletRequest request){
        Map<String,String> params =  threadLocal.get();
        if(params==null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try (InputStream inputStream = request.getInputStream()) {
                params = objectMapper.readValue(inputStream, Map.class);
            } catch (IOException e) {
            }
            if(params==null) params = new HashMap<>();
            threadLocal.set(params);
        }

        return params;
    }
}
