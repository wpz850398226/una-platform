package cn.kunli.una.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控:相当于web.xml
    //因为springboot内置了servlet容器，所以没有web.xml,替代方法ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        //存放初始化参数
        HashMap<String, String> initParameters = new HashMap<>();

        //增加配置
        initParameters.put("loginUsername","una");
        initParameters.put("loginPassword","kunli@888");

        //允许谁可以访问,""代表都可以访问
        initParameters.put("allow","");

        bean.setInitParameters(initParameters);
        return bean;
    }


    //过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();

        bean.setFilter(new WebStatFilter());
        //设置过滤值
        HashMap<String, String> initParameters = new HashMap<>();

        //这些东西不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParameters);
        return bean;
    }
}
