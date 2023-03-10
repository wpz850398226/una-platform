package cn.kunli.una.utils.common;


import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2020年12月3日08:49:57
 * map工具类
 */
public class UnaMapUtil<V> {

    private Map<String, V> innerMap;

    public static <V> UnaMapUtil<V> buildHashMap() {
        return new UnaMapUtil<V>();
    }

    public UnaMapUtil<V> put(String k, V v) {
        if (null == innerMap) {
            innerMap = new HashMap<String,V>();
        }

        innerMap.put(k, v);
        return this;
    }

    public Map<String, V> build() {
        return innerMap;
    }


    /**
     * 获取map中第一个key值
     *
     * @param map 数据源
     * @return
     */
    public static String getKeyOrNull(Map<String, Object> map) {
        String obj = null;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            obj = entry.getKey();
            if (obj != null) {
                break;
            }
        }
        return obj;
    }


    /**
     * 获取map中第一个数据值
     *
     * @param map 数据源
     * @return
     */
    public static Object getFirstOrNull(Map<String, Object> map) {
        Object obj = null;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return obj;
    }

    /**
     * 获取包含一个键值对的map集合
     * @param key
     * @param value
     * @return
     */
    public static Map<String, Object> getMap(String key,Object value) {
        if(StrUtil.isBlank(key)||value==null)return null;
        // 用于存值的 Map 集合
        Map<String, Object> map = new HashMap<>();
        map.put(key,value);
        return map;
    }

    /**
     * 所有键 大写转小写+下划线
     * @param map   需要转换键名的集合
     * @return
     */
    public static Map<String, Object> keysUpperCharToUnderLine(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            result.put(StrUtil.toUnderlineCase(entry.getKey()),entry.getValue());
        }
        return result;
    }

}
