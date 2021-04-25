package cn.kunli.una.utils.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author 作者 : Ponzio
 * @version 创建时间：2020年3月2日11:19:41
 * 类说明 :请求网络接口工具类
 */
@Slf4j
public class HttpUtil {

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        } };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 向指定的URL发送GET方法的请求
     * @param url    发送请求的URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return       远程资源的响应结果
     */
    public static String sendGetHtpps(String url, String param,String authorization) {
        String result = "";
        BufferedReader bufferedReader = null;
        HttpURLConnection conn;
        try {
            //1、读取初始URL
            String urlNameString = url + "?" + param;
            trustAllHosts();
            URL realUrl = new URL(urlNameString);
            // 通过请求地址判断请求类型(http或者是https)
            if (realUrl.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) realUrl.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                conn = https;
            } else {
                conn = (HttpURLConnection) realUrl.openConnection();
            }

            //3、打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //4、设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            //  setRequestProPerty(name,value)
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (StringUtils.isNotBlank(authorization))
                connection.setRequestProperty("Authorization", authorization);
            //5、建立实际的连接
            connection.connect();
            //获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            //遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "---->" + map.get(key));
//            }
            //6、定义BufferedReader输入流来读取URL的响应内容 ，UTF-8是后续自己加的设置编码格式，也可以去掉这个参数
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line = "";
            while (null != (line = bufferedReader.readLine())) {
                result += line;
            }
//			int tmp;
//            while((tmp = bufferedReader.read()) != -1){
//                result += (char)tmp;
//            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("发送GET请求出现异常！！！" + e);
            e.printStackTrace();
        } finally {        //使用finally块来关闭输入流
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求（JSON）：
     * @param url
     * @param JSONBody
     * @return
     * @throws Exception
     */
    public static String sendPostJson(String url, String JSONBody) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(JSONBody));
        CloseableHttpResponse response = httpClient.execute(httpPost);
//		System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
//		System.out.println(responseContent);
        response.close();
        httpClient.close();
        return responseContent;
    }

    /**
     * POST请求（JSON）：
     * @param url
     * @param requestParams
     * @return
     * @throws Exception
     */
    public static String sendPost(String url, Map<String, Object> requestParams) throws Exception {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /** HttpPost */
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, Object>> iterator = requestParams.entrySet().iterator();
//		System.out.println(params.toString());
        while (iterator.hasNext()) {
            Map.Entry<String, Object> en = iterator.next();
            String key = en.getKey();
            Object value = en.getValue();
            if (value != null) {
                params.add(new BasicNameValuePair(key, value.toString()));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        /** HttpResponse */
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                log.info("## release resouce error ##" + e);
            }
        }
        return result;
    }

    /*public static String requestOCRForHttp(String url, Map<String, String> requestParams, String filePathAndName)
            throws Exception {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        *//** HttpPost *//*
        HttpPost httpPost = new HttpPost(url);
        MultipartEntity reqEntity = new MultipartEntity(); // 建立多文件实例
        FileBody filebody = new FileBody(new File(filePathAndName));
        reqEntity.addPart("pic", filebody);// upload为请求后台的File upload;
        for (String key : requestParams.keySet()) {
            String value = requestParams.get(key);
            reqEntity.addPart(key, new StringBody(value, Charset.forName("utf-8")));
        }
        httpPost.setEntity(reqEntity); // 设置实体
        *//** HttpResponse *//*
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                log.info("## release resouce error ##" + e);
            }
        }
        return result;
    }*/


    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    public static HttpSession getSession() {
        HttpSession session = getRequest().getSession();
        return session;
    }

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
     * @Author yang_xp
     * @Description: 发送post请求
     * @Param:
     * @Return:
     */
    public static String doPost(String url, Map<String, Object> map,String token) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        if (StringUtils.isNotBlank(token))
            httpPost.addHeader("token",token);

        // 封装post请求参数
        if (null != map && map.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 向指定的URL发送GET方法的请求
     * @param url    发送请求的URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return       远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader bufferedReader = null;
        try {
            //1、读取初始URL
            String urlNameString = url + "?" + param;
            //2、将url转变为URL类对象
            URL realUrl = new URL(urlNameString);

            //3、打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //4、设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            //5、建立实际的连接
            connection.connect();
            //获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            //遍历所有的响应头字段
            for(String key : map.keySet()) {
                System.out.println(key + "---->" + map.get(key));
            }

            //6、定义BufferedReader输入流来读取URL的响应内容 ，UTF-8是后续自己加的设置编码格式，也可以去掉这个参数
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line = "";
            while(null != (line = bufferedReader.readLine())) {
                result += line;
            }
//			int tmp;
//            while((tmp = bufferedReader.read()) != -1){
//                result += (char)tmp;
//            }

        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("发送GET请求出现异常！！！"  + e);
            e.printStackTrace();
        }finally {        //使用finally块来关闭输入流
            try {
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return result;
    }
}
