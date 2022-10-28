package com.wanxi;

import com.wanxi.controller.LoginController;
import com.wanxi.service.LoginService;
import com.wanxi.vo.ResultInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @description:
 * @author: fxf
 * @date: 2022/9/8 10:22
 */
@SpringBootTest(classes = {Starter.class})
public class TestUser {

    @Autowired
    LoginController loginController;

    @Autowired
    LoginService loginService;

    @Test
    public void test1(){
        ResultInfo info = loginService.login("admin", "123456");
        System.out.println(info);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 测试明文通过PasswordEncoder的encode()方法加密
    @Test
    public void test2(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }


}
