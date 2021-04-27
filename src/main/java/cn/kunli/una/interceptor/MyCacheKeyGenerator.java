package cn.kunli.una.interceptor;


import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.utils.common.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 自定义缓存生成器
 */
@Slf4j
@Component
public class MyCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        if (params.length == 0) return SimpleKey.EMPTY;
        Object param = params[0];
        String className = target.getClass().getSimpleName();
        String key = className+":";
        if (param instanceof String || param instanceof Integer) {
            //参数为id，方法来源：deleteById、getById、ascend
            return key+"(id = "+param+")";
        }else if(param instanceof QueryWrapper){
            //参数为MP条件构造器，方法来源：getOne、list
            QueryWrapper wrapper = (QueryWrapper)param;
            String sqlSegment = wrapper.getExpression().getNormal().getSqlSegment();
            Map map = wrapper.getParamNameValuePairs();
            for (Object o : map.keySet()) {
                String oldChar = "#{ew.paramNameValuePairs."+o.toString()+"}";
                sqlSegment = sqlSegment.replace(oldChar, map.get(o).toString());
            }
            return key+sqlSegment;
        }else if(param != null){
            //参数为实体类，方法来源：save、updateById
            Map map = JSONUtil.toMap(param);
            if(MapUtils.isNotEmpty(map)&&map.containsKey("id")){
                return key+"(id = "+map.get("id")+")";
            }
        }
        return new SimpleKey(params);
    }
}
