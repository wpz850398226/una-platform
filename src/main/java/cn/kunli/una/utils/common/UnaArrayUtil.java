package cn.kunli.una.utils.common;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author 作者 : Ponzio
 * @version 创建时间：2021年4月23日17:21:35
 * 类说明 :操作数组的工具类
 */
public class UnaArrayUtil {

    /**
     * 所有元素 大写转小写+下划线
     * @param array   需要转换键名的集合
     * @return
     */
    public static String[] upperCharToUnderLine(String[] array) {
        if(ArrayUtil.isEmpty(array))return null;
        Integer length = array.length;
        String[] resultArray = new String[length];

        for (Integer i = 0; i < length; i++) {
            resultArray[i] = StrUtil.toUnderlineCase(array[i]);
        }
        return resultArray;
    }
}
