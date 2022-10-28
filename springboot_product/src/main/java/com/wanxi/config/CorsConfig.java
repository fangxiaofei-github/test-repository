package com.wanxi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 配置跨域
 * @author: fxf
 * @date: 2022/9/8 12:49
 */
@Configuration
public class CorsConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("*")
                //.allowedOriginPatterns("*")  //这是springboot2.5.0版本使用的
                .allowCredentials(true)
                .allowedMethods("POST","GET","PUT","DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
