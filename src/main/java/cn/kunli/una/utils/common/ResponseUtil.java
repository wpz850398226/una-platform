package cn.kunli.una.utils.common;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author 作者 : Ponzio
* @version 创建时间：2021年1月6日17:14:28
* 类说明 :关于网络访问响应的工具
*/
public class ResponseUtil {
	public static void responseJson(HttpServletResponse response, int status, Object data) {
        try {
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Methods","*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status);
            response.getWriter().write(JSONObject.toJSONString(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
