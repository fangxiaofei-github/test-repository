package com.wanxi.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: rabbitmq的配置类
 * @author: fxf
 * @date: 2022/9/23 20:10
 */
@Configuration
public class RabbitMQConfig {

    //正常交换机名称
    public static final String MAIL_EXCHANGE = "mail_ex";
    //正常队列名称
    public static final String MAIL_QUEUE = "mail_qu";

    //声明正常交换机，名字叫mail_ex，类型是direct
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(MAIL_EXCHANGE);
    }

    //声明正常队列，名字叫mail_qu
    @Bean
    public Queue queue(){
        return new Queue(MAIL_QUEUE);
    }

    //正常队列绑定正常交换机
    @Bean
    public Binding queueBindingExchange(@Qualifier("queue") Queue queue,
                                        @Qualifier("exchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("mailKey");
    }

}
