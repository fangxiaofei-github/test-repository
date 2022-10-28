package com.wanxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description: 启动类
 * @author: fxf
 * @date: 2022/9/23 17:23
 */

@SpringBootApplication
public class MailStarter {

    public static void main(String[] args) {
        SpringApplication.run(MailStarter.class, args);
    }
}
