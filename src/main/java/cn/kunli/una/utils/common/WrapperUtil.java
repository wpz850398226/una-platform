package cn.kunli.una.utils.common;


import cn.kunli.una.pojo.vo.SysParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public QueryWrapper<T> sysParamToWrapper(SysParam sysParam) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isNotEmpty(sysParam.getAllEqMap()))queryWrapper = this.allEqWrapper(queryWrapper, sysParam.getAllEqMap());
        if(MapUtils.isNotEmpty(sysParam.getNeMap()))queryWrapper = this.neWrapper(queryWrapper,sysParam.getNeMap());
        if(MapUtils.isNotEmpty(sysParam.getGtMap()))queryWrapper = this.gtWrapper(queryWrapper,sysParam.getGtMap());
        if(MapUtils.isNotEmpty(sysParam.getGeMap()))queryWrapper = this.geWrapper(queryWrapper,sysParam.getGeMap());
        if(MapUtils.isNotEmpty(sysParam.getLtMap()))queryWrapper = this.ltWrapper(queryWrapper,sysParam.getLtMap());
        if(MapUtils.isNotEmpty(sysParam.getLeMap()))queryWrapper = this.leWrapper(queryWrapper,sysParam.getLeMap());
        if(MapUtils.isNotEmpty(sysParam.getLikeMap()))queryWrapper = this.likeWrapper(queryWrapper,sysParam.getLikeMap());
        if(MapUtils.isNotEmpty(sysParam.getNotLikeMap()))queryWrapper = this.notLikeWrapper(queryWrapper,sysParam.getNotLikeMap());
        if(MapUtils.isNotEmpty(sysParam.getLikeLeftMap()))queryWrapper = this.likeLeftWrapper(queryWrapper,sysParam.getLikeLeftMap());
        if(MapUtils.isNotEmpty(sysParam.getLikeRightMap()))queryWrapper = this.likeRightWrapper(queryWrapper,sysParam.getLikeRightMap());
        if(ArrayUtils.isNotEmpty(sysParam.getIsNullArray()))queryWrapper = this.isNullWrapper(queryWrapper,sysParam.getIsNullArray());
        if(ArrayUtils.isNotEmpty(sysParam.getIsNotNullArray()))queryWrapper = this.isNotNullWrapper(queryWrapper,sysParam.getIsNotNullArray());
        if(MapUtils.isNotEmpty(sysParam.getInListMap()))queryWrapper = this.inListWrapper(queryWrapper,sysParam.getInListMap());
        if(MapUtils.isNotEmpty(sysParam.getNotInListMap()))queryWrapper = this.notInListWrapper(queryWrapper,sysParam.getNotInListMap());
        if(MapUtils.isNotEmpty(sysParam.getInSqlMap()))queryWrapper = this.inSqlWrapper(queryWrapper,sysParam.getInSqlMap());
        if(MapUtils.isNotEmpty(sysParam.getNotInSqlMap()))queryWrapper = this.notInSqlWrapper(queryWrapper,sysParam.getNotInSqlMap());
        if(ArrayUtils.isNotEmpty(sysParam.getGroupByArray()))queryWrapper = this.groupByWrapper(queryWrapper,sysParam.getGroupByArray());
        if(ArrayUtils.isNotEmpty(sysParam.getOrderByAscArray()))queryWrapper = this.orderByAscWrapper(queryWrapper,sysParam.getOrderByAscArray());
        if(ArrayUtils.isNotEmpty(sysParam.getOrderByDescArray()))queryWrapper = this.orderByDescWrapper(queryWrapper,sysParam.getOrderByDescArray());
        if(MapUtils.isNotEmpty(sysParam.getOrderByMap()))queryWrapper = this.orderByWrapper(queryWrapper,sysParam.getOrderByMap());
        if(StringUtils.isNotBlank(sysParam.getLastStr()))queryWrapper = this.lastWrapper(queryWrapper,sysParam.getLastStr());
        if(StringUtils.isNotBlank(sysParam.getExistsStr()))queryWrapper = this.existsWrapper(queryWrapper,sysParam.getExistsStr());
        if(StringUtils.isNotBlank(sysParam.getNotExistsStr()))queryWrapper = this.notExistsWrapper(queryWrapper,sysParam.getNotExistsStr());
        return queryWrapper;
    }

    /**
     * 获取全等条件的 构造器
     * @param map 条件源
     * @return QueryWrapper<T> 处理过的条件构造器
     */
    public QueryWrapper<T> allEqWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        Map<String, Object> processedMap = MapUtil.keysUpperCharToUnderLine(map);
        queryWrapper.allEq(processedMap);
        return queryWrapper;
    }

    /**
     * 获取不等于 <>条件的 构造器
     * @param map 条件源
     * @return QueryWrapper<T> 处理过的条件构造器
     */
    public QueryWrapper<T> neWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.ne(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取大于条件的 构造器
     * @param map 条件源
     * @return QueryWrapper<T> 处理过的条件构造器
     */
    public QueryWrapper<T> gtWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.gt(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取大于等于条件的 构造器
     * @param map 条件源
     * @return QueryWrapper<T> 处理过的条件构造器
     */
    public QueryWrapper<T> geWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.ge(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取小于条件的 构造器
     * @param map 条件源
     * @return QueryWrapper<T> 处理过的条件构造器
     */
    public QueryWrapper<T> ltWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.lt(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取小于等于条件的 构造器
     * @param map 条件源
     * @return QueryWrapper<T> 处理过的条件构造器
     */
    public QueryWrapper<T> leWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.le(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取相似条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> likeWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.like(StringUtils.isNotBlank(entry.getValue().toString()),StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取不相似条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> notLikeWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.notLike(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取左相似条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> likeLeftWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.likeLeft(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取右相似条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> likeRightWrapper(QueryWrapper<T> queryWrapper,Map<String, Object> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            queryWrapper.likeRight(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取为空条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> isNullWrapper(QueryWrapper<T> queryWrapper, String[] array) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(ArrayUtils.isEmpty(array))return queryWrapper;
        //集合 键名 转为数据库字段
        for (String s : array) {
            queryWrapper.isNull(StringUtil.upperCharToUnderLine(s));
        }
        return queryWrapper;
    }

    /**
     * 获取非空条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> isNotNullWrapper(QueryWrapper<T> queryWrapper, String[] array) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(ArrayUtils.isEmpty(array))return queryWrapper;
        //集合 键名 转为数据库字段
        for (String s : array) {
            queryWrapper.isNotNull(StringUtil.upperCharToUnderLine(s));
        }
        return queryWrapper;
    }

    /**
     * 获取字段 IN条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> inListWrapper(QueryWrapper<T> queryWrapper,Map<String, List<Object>> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
            queryWrapper.in(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取字段 NOT IN条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> notInListWrapper(QueryWrapper<T> queryWrapper,Map<String, List<Object>> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
            queryWrapper.notIn(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取字段 IN ( sql语句 )条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> inSqlWrapper(QueryWrapper<T> queryWrapper, Map<String, String> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, String> entry : map.entrySet()) {
            queryWrapper.inSql(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取字段 NOT IN ( sql语句 )条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> notInSqlWrapper(QueryWrapper<T> queryWrapper, Map<String, String> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, String> entry : map.entrySet()) {
            queryWrapper.notInSql(StringUtil.upperCharToUnderLine(entry.getKey()),entry.getValue());
        }
        return queryWrapper;
    }

    /**
     * 获取非空条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> groupByWrapper(QueryWrapper<T> queryWrapper, String[] array) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(ArrayUtils.isEmpty(array))return queryWrapper;
        //集合 键名 转为数据库字段
        String[] strings = ArrayUtil.upperCharToUnderLine(array);
        queryWrapper.groupBy(strings);
        return queryWrapper;
    }

    /**
     * 获取正序排序条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> orderByAscWrapper(QueryWrapper<T> queryWrapper, String[] array) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(ArrayUtils.isEmpty(array))return queryWrapper;
        //集合 键名 转为数据库字段
        String[] strings = ArrayUtil.upperCharToUnderLine(array);
        queryWrapper.orderByAsc(strings);
        return queryWrapper;
    }

    /**
     * 获取倒叙排序条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> orderByDescWrapper(QueryWrapper<T> queryWrapper, String[] array) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(ArrayUtils.isEmpty(array))return queryWrapper;
        //集合 键名 转为数据库字段
        String[] strings = ArrayUtil.upperCharToUnderLine(array);
        queryWrapper.orderByDesc(strings);
        return queryWrapper;
    }

    /**
     * 获取综合排序条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> orderByWrapper(QueryWrapper<T> queryWrapper, Map<String,Boolean> map) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        //集合 键名 转为数据库字段
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            queryWrapper.orderBy(true,entry.getValue(),StringUtil.upperCharToUnderLine(entry.getKey()));
        }
        return queryWrapper;
    }

    /**
     * 获取 无视优化规则直接拼接到 sql 的最后 条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> lastWrapper(QueryWrapper<T> queryWrapper, String str) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(StringUtils.isBlank(str))return queryWrapper;
        //集合 键名 转为数据库字段
        queryWrapper.last(str);
        return queryWrapper;
    }

    /**
     * 获取 拼接 EXISTS ( sql语句 ) 条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> existsWrapper(QueryWrapper<T> queryWrapper, String str) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(StringUtils.isBlank(str))return queryWrapper;
        //集合 键名 转为数据库字段
        queryWrapper.exists(str);
        return queryWrapper;
    }

    /**
     * 获取 拼接 NOT EXISTS ( sql语句 ) 的最后 条件的 构造器
     * @param key 字段名
     * @param value 字段值
     * @return
     */
    public QueryWrapper<T> notExistsWrapper(QueryWrapper<T> queryWrapper, String str) {
        if(queryWrapper==null)queryWrapper = new QueryWrapper<T>();
        if(StringUtils.isBlank(str))return queryWrapper;
        //集合 键名 转为数据库字段
        queryWrapper.notExists(str);
        return queryWrapper;
    }
}
