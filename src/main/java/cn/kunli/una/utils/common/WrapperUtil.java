package cn.kunli.una.utils.common;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Ponzio
 * @version 2021年4月16日10:22:28
 * mybatis plus wrapper 条件构造器工具类
 */
@Component
public class WrapperUtil<T> {

    public QueryWrapper<T> mapToQueryWrapper(Map<String,Object> map) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        if(MapUtil.isEmpty(map)){
            return queryWrapper;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if(value!=null&&StrUtil.isNotBlank(value.toString())){
                if(key.contains(":")){
                    int index = key.indexOf(":");
                    String condition = key.substring(0, index);
                    String fieldCode = key.substring(index + 1);
                    String column = StrUtil.toUnderlineCase(fieldCode);

                    if(StrUtil.isBlank(fieldCode))continue;

                    switch(condition){
                        case "":
                            queryWrapper.like(column,value);
                            break;
                        case "like":
                            String[] columnArray = column.split(",");
                            Consumer<QueryWrapper<T>> consumer = qw -> {
                                for (String s : columnArray) {
                                    qw.like(s,value).or();
                                }
                            };
                            queryWrapper.and(consumer);
                            break;
                        case "in":
                            queryWrapper.in(column,value.toString().split(","));
                            break;
                        case "notIn":
                            queryWrapper.notIn(column,value.toString().split(","));
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
                    continue;
                }if(key.contains("#")){
                    int index = key.indexOf("#");
                    String item = key.substring(index + 1);
                    switch(item){
                        case "orderArray":
                            String [][] orderArray = (String[][]) value;
                            int length = orderArray.length;
                            for (int i = 0; i < length; i++) {
                                String orderType = orderArray[i][0];
                                String fieldCode = orderArray[i][1];
                                String column = StrUtil.toUnderlineCase(fieldCode);
                                if(orderType.equals("orderByAsc")){
                                    queryWrapper.orderByAsc(column);
                                }else{
                                    queryWrapper.orderByDesc(column);
                                }
                            }
                            break;
                    }
                    continue;
                }else{
                    String[] fieldCodeArray = value.toString().split(",");
                    String[] columnArray = UnaArrayUtil.upperCharToUnderLine(fieldCodeArray);

                    switch(key){
                        case "select":
                            /*for (String s : columnArray) {
                                String carrier = s.substring(s.lastIndexOf("as ") + 1);
                                String humpCarrier = StringUtil.underLineToUpperChar(carrier);
                                s.replace(carrier,humpCarrier);
                            }*/
                            queryWrapper.select(fieldCodeArray);
                            break;
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
                        case "groupBySql":
                            queryWrapper.groupBy(value.toString());
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
                        case "orderArray":
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
                            queryWrapper.eq(StrUtil.toUnderlineCase(key),value);
                            break;
                    }
                }
            }
        }
        return queryWrapper;
    }

    public UpdateWrapper<T> mapToUpdateWrapper(Map<String,Object> map) {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<T>();
        if(MapUtil.isEmpty(map)){
            return updateWrapper;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if(value!=null&&StrUtil.isNotBlank(value.toString())){
                if(key.contains(":")){
                    int index = key.indexOf(":");
                    String condition = key.substring(0, index);
                    String fieldCode = key.substring(index + 1);
                    String column = StrUtil.toUnderlineCase(fieldCode);

                    switch(condition){
                        case "":
                            updateWrapper.like(column,value);
                            break;
                        case "like":
                            updateWrapper.like(column,value);
                            break;
                        case "in":
                            updateWrapper.in(column,value.toString().split(","));
                            break;
                        case "notIn":
                            updateWrapper.notIn(column,value.toString().split(","));
                            break;
                        case "ne"://不等于
                            updateWrapper.ne(column,value);
                            break;
                        case "gt"://大于
                            updateWrapper.gt(column,value);
                            break;
                        case "ge"://大于等于
                            updateWrapper.ge(column,value);
                            break;
                        case "lt"://小于
                            updateWrapper.lt(column,value);
                            break;
                        case "le"://小于等于
                            updateWrapper.le(column,value);
                            break;
                        case "notLike":
                            updateWrapper.notLike(column,value);
                            break;
                        case "likeLeft":
                            updateWrapper.likeLeft(column,value);
                            break;
                        case "likeRight":
                            updateWrapper.likeRight(column,value);
                            break;
                        case "inSql":
                            updateWrapper.inSql(column,value.toString());
                            break;
                        case "notInSql":
                            updateWrapper.notInSql(column,value.toString());
                            break;
                        case "apply":
                            updateWrapper.apply(value.toString());
                            break;
                    }
                    continue;
                }

                if(key.contains("#")){
                    int index = key.indexOf("#");
                    String item = key.substring(index + 1);
                    switch(item){
                        case "orderArray":
                            String [][] orderArray = (String[][]) value;
                            int length = orderArray.length;
                            for (int i = 0; i < length; i++) {
                                String orderType = orderArray[i][0];
                                String fieldCode = orderArray[i][1];
                                String column = StrUtil.toUnderlineCase(fieldCode);
                                if(orderType.equals("orderByAsc")){
                                    updateWrapper.orderByAsc(column);
                                }else{
                                    updateWrapper.orderByDesc(column);
                                }
                            }
                            break;
                    }
                    continue;
                }else{
                    String[] fieldCodeArray = value.toString().split(",");
                    String[] columnArray = UnaArrayUtil.upperCharToUnderLine(fieldCodeArray);

                    switch(key){
                        case "isNull":
                            for (String column : columnArray) {
                                updateWrapper.isNull(column);
                            }
                            break;
                        case "isNotNull":
                            for (String column : columnArray) {
                                updateWrapper.isNotNull(column);
                            }
                            break;
                        case "groupBy":
                            for (String column : columnArray) {
                                updateWrapper.groupBy(column);
                            }
                            break;
                        case "groupBySql":
                            updateWrapper.groupBy(value.toString());
                            break;
                        case "orderByAsc":
                            for (String column : columnArray) {
                                updateWrapper.orderByAsc(column);
                            }
                            break;
                        case "orderByDesc":
                            for (String column : columnArray) {
                                updateWrapper.orderByDesc(column);
                            }
                            break;
                        case "orderArray":
                            for (String column : columnArray) {
                                updateWrapper.orderByDesc(column);
                            }
                            break;
                        case "last":
                            updateWrapper.last(value.toString());
                            break;
                        case "exists":
                            updateWrapper.exists(value.toString());
                            break;
                        case "notExists":
                            updateWrapper.notExists(value.toString());
                            break;
                        case "setSql":
                            updateWrapper.setSql(value.toString());
                            break;
                        default:
                            //其他条件默认为精确匹配
                            updateWrapper.eq(StrUtil.toUnderlineCase(key),value);
                            break;
                    }
                }
            }
        }
        return updateWrapper;
    }
}
