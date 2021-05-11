package cn.kunli.una;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.kunli.una.mapper")      //mybatis扫描包
@EnableTransactionManagement
//开启security注解
@EnableAspectJAutoProxy(proxyTargetClass=true, exposeProxy=true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class UnaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnaApplication.class, args);
    }

}
