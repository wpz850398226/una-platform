package cn.kunli.una;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cn.kunli.una.mapper")      //mybatis扫描包
@EnableTransactionManagement
//开启security注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class UnaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnaApplication.class, args);
    }

}
