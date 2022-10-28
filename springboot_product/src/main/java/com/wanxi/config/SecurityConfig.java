package com.wanxi.config;

import com.wanxi.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: security的配置类
 * @author: fxf
 * @date: 2022/9/17 22:42
 */
@EnableGlobalMethodSecurity(prePostEnabled=true) //开启基于注解的权限控制方案
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //注入密码解析器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //注入AuthenticationManager对象，在业务层的登录方法要使用到
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //把jwt的认证过滤器注入进来
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //授权认证
        http.authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/login").anonymous()
                // security需要的自定义登录接口，并且要把这个登录接口放行
                // security需要的自定义登录接口，并且要把这个登录接口放行
                .antMatchers("/user/login").anonymous()
                // 除上面外的所有请求全部不需要认证即可访问
                //.anyRequest().permitAll();
                //无需认证的为static下的静态资源，以及/index请求
                .antMatchers("/static/**").permitAll()
                // TODO 这里应该配置的是所有的请求都需要认证才能访问
                .anyRequest().authenticated();

        //关闭csrf
        http.csrf()
                .disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //把jwt认证过滤器添加到过滤器链中去
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * 建立SpringBoot项目时，在pom文件中如果引入了SpringSecurity的相关依赖，Spring-boot-starter-security后，
     * SpringBoot就会默认开启身份权限验证，同时也默认开启了防CSRF（跨站请求伪造）的校验，所以会将post请求拦截，即报错403 forbidden，而get请求不会被拦截
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //利用ignore忽略拦截
        web.ignoring().antMatchers("/**");
    }
}
