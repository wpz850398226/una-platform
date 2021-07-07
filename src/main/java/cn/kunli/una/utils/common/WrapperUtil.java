package cn.kunli.una.utils.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Ponzio
 * @version 2021年4月16日10:22:28
 * mybatis plus wrapper 条件构造器工具类
 */
@Component
public class WrapperUtil<T> {

    public QueryWrapper<T> mapToWrapper(Map<String,Object> map) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        if(MapUtils.isEmpty(map))return queryWrapper;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if(value!=null&&StringUtils.isNotBlank(value.toString())){
                if(key.contains(":")){
                    int index = key.indexOf(":");
                    String condition = key.substring(0, index);
                    String fieldCode = key.substring(index + 1);
                    String column = StringUtil.upperCharToUnderLine(fieldCode);

                    switch(condition){
                        case "":
                            queryWrapper.like(column,value);
                            break;
                        case "like":
                            queryWrapper.like(column,value);
                            break;
                        case "in":
                            queryWrapper.in(column,value.toString().split(","));
                            break;
                        case "ne"://不等于
                            queryWrapper.ne(column,value);
                            break;
                        case "gt"://大于
                            queryWrapper.gt(column,value);
                            break;
                        case "ge"://大于等于
                            queryWrapper.ge(column,value);
                            break;
                        case "lt"://小于
                            queryWrapper.lt(column,value);
                            break;
                        case "le"://小于等于
                            queryWrapper.le(column,value);
                            break;
                        case "notLike":
                            queryWrapper.notLike(column,value);
                            break;
                        case "likeLeft":
                            queryWrapper.likeLeft(column,value);
                            break;
                        case "likeRight":
                            queryWrapper.likeRight(column,value);
                            break;
                        case "inSql":
                            queryWrapper.inSql(column,value.toString());
                            break;
                        case "notInSql":
                            queryWrapper.notInSql(column,value.toString());
                            break;
                        case "apply":
                            queryWrapper.apply(value.toString());
                            break;
                    }
                }else{
                    String[] fieldCodeArray = value.toString().split(",");
                    String[] columnArray = ArrayUtil.upperCharToUnderLine(fieldCodeArray);

                    switch(key){
                        case "isNull":
                            for (String column : columnArray) {
                                queryWrapper.isNull(column);
                            }
                            break;
                        case "isNotNull":
                            for (String column : columnArray) {
                                queryWrapper.isNotNull(column);
                            }
                            break;
                        case "groupBy":
                            for (String column : columnArray) {
                                queryWrapper.groupBy(column);
                            }
                            break;
                        case "orderByAsc":
                            for (String column : columnArray) {
                                queryWrapper.orderByAsc(column);
                            }
                            break;
                        case "orderByDesc":
                            for (String column : columnArray) {
                                queryWrapper.orderByDesc(column);
                            }
                            break;
                        case "last":
                            queryWrapper.last(value.toString());
                            break;
                        case "exists":
                            queryWrapper.exists(value.toString());
                            break;
                        case "notExists":
                            queryWrapper.notExists(value.toString());
                            break;
                        default:
                            //其他条件默认为精确匹配
                            queryWrapper.eq(StringUtil.upperCharToUnderLine(key),value);
                            break;
                    }
                }
            }
        }
        return queryWrapper;
    }
}
