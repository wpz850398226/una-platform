package cn.kunli.una.utils.common;

import java.lang.reflect.Field;

/**
 * @author Ponzio
 * @version 2020年8月3日11:50:53
 * java反射工具类
 */
public class ReflectUtil {


	//前端传回查询list，格式化为查询字符串
	public static boolean whetherHaveField(Object obj,String fieldName) {
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			if(field!=null)return true;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return false;
	}

}
