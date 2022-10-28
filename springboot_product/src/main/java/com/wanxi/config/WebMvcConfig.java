package com.wanxi.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 解决SpringBoot上传图片，无法立即访问
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  /**
   * 图片虚拟地址映射
   * @param registry
   * 设置该映射之后，外网只能访问本地的images文件内部的资源
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/images/**")
            .addResourceLocations("file:D:\\workspace\\idea\\springboot_test\\springboot_product\\src\\main\\resources\\static\\images\\");
  }

}