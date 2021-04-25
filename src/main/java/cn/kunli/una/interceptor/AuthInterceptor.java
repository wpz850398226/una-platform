package cn.kunli.una.interceptor;


import cn.kunli.una.annotation.DisableAuth;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.vo.SysHttpCode;
import cn.kunli.una.utils.common.HttpUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 验证拦截器
 */
@Slf4j
@Component
public class AuthInterceptor extends BaseInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
//        log.info("================进入拦截器======================");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //放行逻辑
        HandlerMethod method = (HandlerMethod) handler;
        DisableAuth auth = method.getMethod().getAnnotation(DisableAuth.class);
        if (isDisableAuth(auth)) {
            return super.preHandle(request, response, handler);
        }
        //获取token
        String accessToken = getAuthToken(request);
        if (StringUtils.isEmpty(accessToken)) {
            /*String userAgent = request.getHeader("user-agent");
            if(userAgent.indexOf("Windows") != -1||userAgent.indexOf("Macintosh") != -1){
                HttpUtil.redirect(request,response,"/sys/login/toLogin",null);
            }else{
                setResponse(request, response, failCode+"","Error: token is invalid");
            }*/
            setResponse(request, response, SysHttpCode.BAD_REQUEST +"","Error: token is invalid");
            return false;
        }

        //查询token是否正确
        Object object = redisUtil.get(accessToken);
        if (object == null) {
            String userAgent = request.getHeader("user-agent");
            if(userAgent.indexOf("Windows") != -1||userAgent.indexOf("Macintosh") != -1){
                HttpUtil.redirect(request,response,"/sys/login/toLogin",null);
            }else{
                setResponse(request, response, SysHttpCode.BAD_REQUEST+"","Error: token is invalid");
            }
            return false;
        }

        // 将user写入到request请求中
        SysAccount sysAccount = (SysAccount) object;
        request.setAttribute("userId", sysAccount.getId());
        request.setAttribute("deptId", sysAccount.getDepartmentId());
        request.setAttribute("comId", sysAccount.getCompanyId());
        return true;
    }

    private static boolean isDisableAuth(DisableAuth auth) {
        return auth != null;
    }

    /**
     * 获取http请求头部或者参数中的token值
     *
     * @param request
     *            http请求传递的值
     * @return 返回token
     */
    private String getAuthToken(HttpServletRequest request) {
        String token = request.getHeader("token");

        if (token == null) {
            token = request.getParameter("token");
            if(token==null){
                if(!request.getDispatcherType().name().equals("ERROR")){
                    /*Subject subject = SecurityUtils.getSubject();
                    if(subject!=null){
                        token = subject.getSession().getAttribute("token").toString();
                    }*/
                }
            }
        }
        return token;
    }
}
