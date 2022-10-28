package com.wanxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试控制层
 * @author: fxf
 * @date: 2022/9/17 9:44
 */
@Api(tags = "测试控制层模块")
@RestController
public class TestController {


    @ApiOperation("测试接口")
    @RequestMapping("/hello")
    //@PreAuthorize("hasAuthority('test')")
    public String hello(){
        return "hello";
    }
}
