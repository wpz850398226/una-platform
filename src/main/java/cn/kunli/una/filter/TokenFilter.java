package cn.kunli.una.filter;

import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.security.TokenService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: token过滤器
 * @author: Ponzio
 * @create: 2021年1月7日09:48:23
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserDetailsService userDetailsService;
    private static final String TOKEN_KEY = "token";

    private Long MINUTES_10 = 10*60*1000L;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取前端请求的token
        String token = getToken(request);
        if(StrUtil.isNotBlank(token)){
            //token获取登录用户信息
            SysLoginAccountDetails loginUser = tokenService.getLoginAccount(token);
            if(loginUser!=null){
                //刷新token
                loginUser = checkLoginTime(loginUser);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }

    /**
     * 校验时间
     * 如果过期时间晚于当前时间-10分钟，自动刷新缓存
     * @param loginUser
     * @return
     */
    public SysLoginAccountDetails checkLoginTime(SysLoginAccountDetails loginUser){
        Long expireTime = loginUser.getExpireTime();
        long currentTimeMillis = System.currentTimeMillis();
        if(expireTime - currentTimeMillis <= MINUTES_10){
            String token = loginUser.getToken();
            loginUser = (SysLoginAccountDetails)userDetailsService.loadUserByUsername(loginUser.getUsername());
            loginUser.setToken(token);
            tokenService.refresh(loginUser);
        }
        return loginUser;
    }

    /**
     * 根据参数或header头信息获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        String token = request.getParameter(TOKEN_KEY);
        if(StrUtil.isBlank(token)){
            token = request.getHeader(TOKEN_KEY);
            if(token!=null&&token.equals("null"))token = null;
        }

        return token;
    }

}
