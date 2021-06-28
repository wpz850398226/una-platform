package cn.kunli.una.interceptor;


import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.utils.common.JSONUtil;
import cn.kunli.una.utils.common.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
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
        }else if(param instanceof HashMap){
            //参数为HashMap，方法来源：selectOne、selectList
            Map<String,Object> map = (HashMap)param;
            StringBuffer sb = new StringBuffer();
            sb.append("(");
            for (String mapKey : map.keySet()) {
                sb.append(",").append(StringUtil.upperCharToUnderLine(mapKey)).append(" = ").append(map.get(mapKey).toString());
            }
            sb.append(")");
            sb.replace(1,2,"");
            return key+sb.toString();
        }else if(param instanceof BasePojo){
            BasePojo basePojo = (BasePojo)param;
            if(basePojo.getId()!=null){
                return key+"(id = "+basePojo.getId()+")";
            }
        }
        return new SimpleKey(params);
    }
}
