package cn.kunli.una.utils.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 作者 : Ponzio
 * @version 创建时间：2020年3月2日11:19:41
 * 类说明 :请求网络接口工具类
 */
@Slf4j
public class HttpUtil {

    /**
     * 对于请求是ajax请求重定向问题的处理方法
     * @param request
     * @param response
     * @param newPath	重定向或转发的新路径
     * @param oldPath	原访问路径
     * @throws Exception
     */
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String newPath, String oldPath) throws Exception {
        //获取当前请求的路径
        HttpSession session = request.getSession();
        if(oldPath!=null&&!oldPath.equals(""))request.getSession().setAttribute("oldPath",oldPath);
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理 否则直接重定向就可以了
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("PATH", basePath+newPath);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            request.getRequestDispatcher(newPath).forward(request, response);
        }
    }

    /**
     * 向指定的URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return 远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, Object> header, Map<String, Object> param) {
        log.info("[supervisemodel] start sendGet-------> url: <{}>", url);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach((k, v) -> {
                headers.add(k, v.toString());
            });
        }

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(null, headers);

        if (MapUtils.isNotEmpty(param)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(url);
            param.forEach((k, v) -> {
                stringBuffer.append("?").append(k).append("=").append(v);
            });
            url = stringBuffer.toString();
        }

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return exchange.getBody();
    }

    /**
     * POST请求：
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String sendPost(String url, Map<String, Object> header, Map<String, Object> param) {
        log.info("[supervisemodel] start sendPost-------> url: <{}>", url);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach((k, v) -> {
                headers.add(k, v.toString());
            });
        }

        if (MapUtils.isNotEmpty(param)) {
            param.forEach((k, v) -> {
                map.add(k,v.toString());
            });
        }

        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        return result;
    }

}
