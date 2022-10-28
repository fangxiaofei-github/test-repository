package com.wanxi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: swagger配置类
 * @author: fxf
 * @date: 2022/9/26 10:35
 */
@EnableSwagger2 //开启
@Configuration
public class Swagger2Config  {
    /**
     * 此方法主要是去规定扫描哪些包下面的生成swagger文档
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) //表示使用swagger2文档类型
                .select()  //文档通过一系列的选择器组成api和path
                //可以获取指定的api即指定哪些controller的接口生成文档。
                //.apis(RequestHandlerSelectors.basePackage("com.wanxi.TestController"))
                //当然可以获取所有接口：apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.any())
                //在查找出来的接口中进行筛选
                .paths(PathSelectors.any())
                .build()
                //文档的介绍，调用的是下面的apiInfo()方法
                .apiInfo(apiInfo());
    }

    // 自定义文档的介绍
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目管理接口API文档参考")
                //.description("文档的描述")
                .version("1.0")
                .build();
    }
}
