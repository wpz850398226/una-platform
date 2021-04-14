package cn.kunli.una.pojo.vo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年10月9日 上午9:43:58
 * 公共父类，封装通用请求参数
 */
public class SysParam extends HashMap implements Map{

	Map map = null;

	HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}






}
