package cn.kunli.una.utils.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Ponzio
 * @version 2021年4月16日10:22:28
 * mybatis plus wrapper 条件构造器工具类
 */
@Component
public class WrapperUtil<T> {

    /**
     * 获取全等条件的 构造器
     *
     * @param map 数据源
     * @return
     */
    public QueryWrapper<T> allEqWrapper(Map<String, Object> map) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        //集合 键名 转为数据库字段
        Map<String, Object> processedMap = MapUtil.keysUpperCharToUnderLine(map);
        queryWrapper.allEq(processedMap);
        return queryWrapper;
    }

    /**
     * 获取相似条件的 构造器
     *
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> likeWrapper(String key, Object value) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        queryWrapper.like(StringUtil.upperCharToUnderLine(key),value);
        return queryWrapper;
    }

}
