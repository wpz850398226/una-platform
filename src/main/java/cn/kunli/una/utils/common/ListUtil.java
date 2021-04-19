package cn.kunli.una.utils.common;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者 : Ponzio
 * @version 创建时间：2019年7月23日 下午9:38:15
 * 类说明 :操作集合的工具类
 */
public class ListUtil {

    /**
     * 拆分集合
     *
     * @param <T>
     * @param resList 要拆分的集合
     * @param count   每个集合的元素个数
     * @return 返回拆分后的各个集合
     */

    public static <T> List<List<T>> splitList(List<T> resList, int count) {


        if (resList == null || count < 1)

            return null;


        List<List<T>> ret = new ArrayList<List<T>>();

        int size = resList.size();

        if (size <= count) { //数据量不足count指定的大小

            ret.add(resList);

        } else {

            int pre = size / count;

            int last = size % count;

            //前面pre个集合，每个大小都是count个元素

            for (int i = 0; i < pre; i++) {

                List<T> itemList = new ArrayList<T>();

                for (int j = 0; j < count; j++) {

                    itemList.add(resList.get(i * count + j));

                }

                ret.add(itemList);

            }

            //last的进行处理

            if (last > 0) {

                List<T> itemList = new ArrayList<T>();

                for (int i = 0; i < last; i++) {

                    itemList.add(resList.get(pre * count + i));

                }

                ret.add(itemList);

            }

        }

        return ret;
    }

    public static <T>boolean isNotNull(List<T> list){
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }

    //字符串集合转字符串逗号分隔
    public static String listToStr(List<String> list){
        if(list!=null&&list.size()>0){
            Object[] array = list.toArray();
            String str = StringUtils.join(array, ",");
            return str;
        }else {
            return null;
        }
    }

    /*
     * 实体类集合转为map集合，反射获取实体类字段
     * */
    public static List<Map<String, Object>> entityListToMapList(List<?> objs) {
        if(objs==null)return null;
        // 用于存放多个对象的集合
        List<Map<String, Object>> pdResult = new ArrayList<>();
        // 遍历方法参数中的集合
        for (Object obj : objs) {
            // 用于封装单个对象 get 方法返回值的 Map 集合
            Map<String, Object> pdMap = new HashMap<>();
            // 通过反射获取该对象的方法对象数组
            Method[] methods = obj.getClass().getDeclaredMethods();
            // 遍历方法对象数组
            for (Method method : methods) {
                // 获取方法名称
                String methodName = method.getName();
                // 判断该方法是否名称不为 null ，并且名称是以 get 开头，满足条件进入 if 中
                if (methodName != null && methodName.startsWith("get")) {
                    // 设置方法的访问权限
                    method.setAccessible(true);
                    try {
                        // 将方法名的 get 前缀去掉，并增加 pd 前缀
                        //String pdKey = "pb".concat(methodName.substring(3));
                        String pdKey = StringUtil.lowerInitial(methodName.substring(3));
                        // 将 get 方法的名称作为 Map 的 key，将返回值作为 value 进行封装
                        Object val = method.invoke(obj, null);
                        if(val!=null)pdMap.put(pdKey, val);
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
            }
            // 将封装好的 Map 集合添加到 List 集合中
            pdResult.add(pdMap);
        }
        return pdResult;
    }

    /*
     * 实体类集合转为map集合，反射获取实体类字段
     * */
    public static Map<String, Object> entityToMap(Object obj) {
        if(obj==null)return null;
        // 用于封装单个对象 get 方法返回值的 Map 集合
        Map<String, Object> pdMap = new HashMap<>();
        // 通过反射获取该对象的方法对象数组
        Method[] methods = obj.getClass().getDeclaredMethods();
        // 遍历方法对象数组
        for (Method method : methods) {
            // 获取方法名称
            String methodName = method.getName();
            // 判断该方法是否名称不为 null ，并且名称是以 get 开头，满足条件进入 if 中
            if (methodName != null && methodName.startsWith("get")) {
                // 设置方法的访问权限
                method.setAccessible(true);
                try {
                    // 将方法名的 get 前缀去掉，并增加 pd 前缀
                    //String pdKey = "pb".concat(methodName.substring(3));
                    String pdKey = StringUtil.lowerInitial(methodName.substring(3));
                    // 将 get 方法的名称作为 Map 的 key，将返回值作为 value 进行封装
                    Object val = method.invoke(obj, null);
                    if(val!=null)pdMap.put(pdKey, val);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
        return pdMap;
    }

    /**
     * 所有元素 大写转小写+下划线
     * @param list   需要转换键名的集合
     * @return
     */
    public static List<String> upperCharToUnderLine(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(StringUtil.upperCharToUnderLine(s));
        }
        return result;
    }
}
