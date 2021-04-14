package cn.kunli.una.utils.common;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * token工具
 * @author Ponzio
 * 时间：2020年2月15日11:58:29
 */
public class TokenUtil {

	/**
	 * 校验token
	 * @param token		移动端传回的token
	 * @param url		访问地址url（ip后面，？前面的地址）
	 * @return
	 */
	public static boolean verify(String token,String url){
		String text = url + TimeUtil.getDayBegin();
		if(DigestUtils.md5Hex(text).equals(token)){
			return true;
		}
		return false;
	}
	
}
