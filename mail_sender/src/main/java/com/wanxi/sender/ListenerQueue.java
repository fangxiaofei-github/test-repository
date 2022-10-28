package com.wanxi.sender;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @description: 消费者：用来监听队列
 * @author: fxf
 * @date: 2022/9/23 20:29
 */
@Component
public class ListenerQueue {

    //logger日志，出错时方便排错，这个可有可无
    public static final Logger LOGGER = LoggerFactory.getLogger(ListenerQueue.class);

    //邮件发送器
    @Autowired
    private JavaMailSender javaMailSender;

    //邮件的属性信息(即yml配置文件中配置的)都封装在这个MailProperties对象中了
    @Autowired
    private MailProperties mailProperties;

    //这个对象和thymeleaf有关
    @Autowired
    private TemplateEngine templateEngine;



    // 监听队列
    @RabbitListener(queues = "mail_qu")
    public void sendMail(Message message){
        // 获取传递过来的信息(nickname-email)
        String msg = new String(message.getBody());
        String[] values = msg.split("-");
        String nickname = values[0];
        String email = values[1];

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 获取附件信息对象
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            //发件人
            helper.setFrom(mailProperties.getUsername());
            //收件人
            helper.setTo(email);
            //主题
            helper.setSubject("入职欢迎");
            //发送日期
            helper.setSentDate(new Date());
            //邮件内容
            Context context = new Context();
            //把需要的信息存到Context对象中（注意这个Context类是thymeleaf包下的）
            context.setVariable("username", nickname);
            //然后把context传给mail.html
            String mail = templateEngine.process("mail", context);
            //true 设置以html形式发送
            helper.setText(mail,true);
            //发送邮件
            javaMailSender.send(mimeMessage);
            LOGGER.info("邮件发送成功");
        } catch (Exception e) {
            LOGGER.error("邮件发送失败=========>{}", e.getMessage());
        }
    }

}
