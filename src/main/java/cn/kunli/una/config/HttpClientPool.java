package cn.kunli.una.config;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * HttpClient工具类,注入连接池等，用在工具类获取httpclient
 * </P>
 *
 * @Author 王文龙
 * @Date 2019-08-16 11:10
 */
@Configuration
public class HttpClientPool {

    //最大连接数
    @Value("${http.maxTotal}")
    private Integer maxTotal;

    //并发数
    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;

    //创建连接的最长时间
    @Value("${http.connectTimeout}")
    private Integer connectTimeout;

    //从连接池中获取到连接的最长时间
    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;

    //数据传输的最长时间
    @Value("${http.socketTimeout}")
    private Integer socketTimeout;

    //提交请求前测试连接是否可用
    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;

    /**
     * 首先实例化一个连接池管理器，设置最大连接数、并发连接数
     *
     * @return httpClientConnectionManager
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);

        //并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }

    /**
     * 实例化连接池，设置连接池管理器
     * 这里需要以参数形式注入上面实例化的连接池管理器
     *
     * @param httpClientConnectionManager 连接池管理器
     * @return httpClientBuilder 连接池
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {
        //HttpClientBuilder中的构造方法被protected修饰，所以这里不能直接使用new来实例化一个HttpClientBuilder，可以使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient
     *
     * @param httpClientBuilder 连接池
     * @return 获取httpclient
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }


    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性，有需要的话可以在此设置
     *
     * @return builder
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        //将属性 set进 builder 中
        builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
        return builder;
    }

    /**
     * 使用builder构建一个RequestConfig对象
     *
     * @param builder 连接信息
     * @return builder.build()
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }


}