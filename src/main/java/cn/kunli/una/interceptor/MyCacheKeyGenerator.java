package cn.kunli.una.interceptor;


import cn.kunli.una.annotation.DisableAuth;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.vo.SysHttpCode;
import cn.kunli.una.utils.common.HttpUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * token 验证拦截器
 */
@Slf4j
@Component
public class MyCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        if (params.length == 0) {
            return SimpleKey.EMPTY;
        }
        Object param = params[0];
        String className = target.getClass().getSimpleName();
        if (param instanceof String || param instanceof Integer) {
            return className+"-"+param;
        }else if(param instanceof BasePojo){
            BasePojo basePojo = (BasePojo)param;
            if(basePojo.getId()!=null){
                return className+"-"+basePojo.getId();
            }
        }
        return new SimpleKey(params);
    }
}
