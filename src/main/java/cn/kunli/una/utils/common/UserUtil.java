package cn.kunli.una.utils.common;


import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;

/**
 * @author Ponzio
 * @version 2020年12月3日08:49:57
 * 用户工具类
 */
public class UserUtil {


    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static SysLoginAccountDetails getLoginAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            Object principal = authentication.getPrincipal();
            if(principal instanceof SysLoginAccountDetails)return (SysLoginAccountDetails) principal;
        }
        return null;
    }

    /**
     * 判断是否有权限
     *
     * @return
     */
    public static boolean isPermitted(String... permissions) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (String permission : permissions) {
                boolean contains = authorities.contains(new SimpleGrantedAuthority(permission));
                if(contains) return true;
            }
        }
        return false;
    }




}
