package cn.kunli.una.utils.common;


import java.lang.reflect.Field;

/**
 * @author Ponzio
 * @version 2020年12月3日08:49:57
 * map工具类
 */
public class EntityUtil {

    public static Object getFieldValue(Class entityClass,Object entity,String field) {
        try {
            //获取字段
            Field parentField = entityClass.getDeclaredField(field);
            parentField.setAccessible(true);
            //获取父字段值
            Object valueObj = parentField.get(entity);
            return valueObj;
        } catch (NoSuchFieldException  e) {
            if(entityClass.getSuperclass()!=null){
                return getFieldValue(entityClass.getSuperclass(),entity,field);
            }else{
                e.printStackTrace();
            }
        } catch (IllegalAccessException  e) {
            e.printStackTrace();
        }
        return null;
    }

}
