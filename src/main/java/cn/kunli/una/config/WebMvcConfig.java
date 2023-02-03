package cn.kunli.una.config;

import cn.kunli.una.handler.MyMethodSecurityExpressionHandler;
import cn.kunli.una.handler.GlobalExceptionHandler;
import cn.kunli.una.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域访问
 * 正式部署请注释掉此文件全部代码
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加视图解析器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login/login");
    }

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:/"+fileUrl);
    }*/


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*") //.allowedMethods("PUT", "DELETE","POST","GET")
                .allowedHeaders("*").maxAge(3600);
    }

    @Bean
    public AuthInterceptor getAuthInterceptor(){
        return new AuthInterceptor();
    }

    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    //注册拦截器
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //SpringMVC下，拦截器的注册需要排除对静态资源的拦截(*.css,*.js)
        //SpringBoot已经做好了静态资源的映射，因此我们无需任何操作
        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/login.html"
                        ,"/","/sys/login/login","/sys/login/toLogin"
                        ,"/api/access/insertAccess"
                        ,"/positioning/addPositioning");
    }*/

    //自定义鉴权注解-鉴权方法
    @Bean
    MyMethodSecurityExpressionHandler customMethodSecurityExpressionHandler() {
        return new MyMethodSecurityExpressionHandler();
    }


}
