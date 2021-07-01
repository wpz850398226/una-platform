package cn.kunli.una.config;

import cn.kunli.una.filter.UserAuthenticationFilter;
import cn.kunli.una.filter.TokenFilter;
import cn.kunli.una.handler.MyAuthenticationFailureHandler;
import cn.kunli.una.handler.MyAuthenticationSuccessHandler;
import cn.kunli.una.handler.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenFilter tokenFilter;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;


    //配置密码编码器
    @Bean
    public PasswordEncoder getPW(){
        return new BCryptPasswordEncoder();
    }

    //配置用户信息服务（查询用户信息）
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }


    //配置安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //表单提交
        http.formLogin()
                //自定义登录页面
                .loginPage("/duohui/login/toLogin")
                //登录表单提交地址
                .loginProcessingUrl("/sys/login/login")
                //登录成功后跳转的页面，必须是post请求
//                .successForwardUrl("/sys/index")
                //自定义登录成功处理器
                .successHandler(myAuthenticationSuccessHandler)
                //自定义登录失败处理器
                .failureHandler(new MyAuthenticationFailureHandler())
                .and().logout().logoutUrl("/sys/login/logout")//开启注销功能
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and().rememberMe()//开启记住我功能，默认保存2周
                .and().csrf().disable();    //关闭csrf功能，类似防火墙

        //拦截器/授权
        http.authorizeRequests()
                //放行登录页
                .antMatchers("/sys/login/toLogin").permitAll()
                //放行静态资源
                .antMatchers("/una/**","/xadmin/**","/layui/**","/duohui/**").permitAll()
                //所有请求都必 须认证才能访问，必须登录
                .anyRequest().authenticated();
                //自定义access方法
                //.anyRequest().access("@myServiceImpl.hasPermission(request,authentication)");

        //允许显示在iframe中
        http.headers().frameOptions().disable();

        //userAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        //添加过滤器
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(UserAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    UserAuthenticationFilter UserAuthenticationFilterBean() throws Exception {
        userAuthenticationFilter.setAuthenticationManager(authenticationManager());
        userAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        return userAuthenticationFilter;
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        try {
            //实现认证接口，自定义认证方法
            auth.userDetailsService(userDetailsService)
                    //.inMemoryAuthentication()
                    .passwordEncoder(new BCryptPasswordEncoder());
            //.withUser("Ponzio").password(new BCryptPasswordEncoder().encode("123456")).roles("admin")
            //.and().withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("admin");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

