package cn.kunli.una.utils.common;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @program:
 * @description:
 * @author: yang_xp
 * @create: 2019-12-03 15:04
 **/
public class JSONUtil {


    /**
     * 将任意类型转换成字符串
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        Class<?> entityClass = value.getClass();
        if (entityClass == int.class || entityClass == Integer.class) {
            return value + "";
        } else if (entityClass == String.class) {
            return (String) value;
        } else if (entityClass == long.class || entityClass == Long.class) {
            return value + "";
        } else {
            return JSON.toJSONString(value);
        }
    }


    /**
     * 把一个字符串转换成bean对象
     *
     * @param str
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> entityClass) {
        if (str == null || str.length() <= 0 || entityClass == null) {
            return null;
        }
        if (entityClass == int.class || entityClass == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (entityClass == String.class) {
            return (T) str;
        } else if (entityClass == long.class || entityClass == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), entityClass);
        }
    }


    /**
     * 将Javabean转换为Map
     *
     * @param javaBean javaBean
     * @return Map对象
     */
    public static Map toMap(Object javaBean) {

        Map result = new HashMap();
        Method[] methods = javaBean.getClass().getDeclaredMethods();

        for (Method method : methods) {

            try {

                if (method.getName().startsWith("get")) {

                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(javaBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;

    }

    /**
     * 将Javabean转换为Map(包含父类字段）
     *
     * @param entity
     * @return Map对象
     */
    public static Map toMapWithParent(Object entity) throws IllegalAccessException{

        Map map = new HashMap();
        Class<?> entityClass = entity.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (entityClass != null){
            fieldList.addAll(Arrays.asList(entityClass.getDeclaredFields()));
            entityClass = entityClass.getSuperclass();//找到所有父类的属性
        }
        for (Field field : fieldList) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if(null != value)map.put(field.getName(), value);
        }
        return map;

    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> objList) {
        List<Map<String, Object>> list = Collections.emptyList();
        if (objList != null && objList.size() > 0) {
            list = new ArrayList<>(objList.size());
            Map<String, Object> map;
            T bean;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = toMap(bean);
                list.add(map);
            }
        }
        return list;
    }

}
