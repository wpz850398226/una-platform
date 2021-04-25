package cn.kunli.una.interceptor;


import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 自定义缓存驱逐器
 */
@Slf4j
@Component
public class MyCacheKeyEvictor implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        if (params.length == 0) {
            return SimpleKey.EMPTY;
        }
        Object param = params[0];
        String className = target.getClass().getSimpleName();
        String methodName = method.getName();
        String key = className+":"+methodName+":";
        if (param instanceof String || param instanceof Integer) {
            return key+param;
        }else if(param instanceof QueryWrapper){
            QueryWrapper wrapper = (QueryWrapper)param;
            String sqlSegment = wrapper.getExpression().getNormal().getSqlSegment();
            Map map = wrapper.getParamNameValuePairs();
            for (Object o : map.keySet()) {
                String oldChar = "#{ew.paramNameValuePairs."+o.toString()+"}";
                sqlSegment = sqlSegment.replace(oldChar, map.get(o).toString());
            }
            return key+sqlSegment;
        }else if(param instanceof BasePojo){
            BasePojo basePojo = (BasePojo)param;
            if(basePojo.getId()!=null){
                return key+basePojo.getId();
            }
        }
        return new SimpleKey(params);
    }
}
