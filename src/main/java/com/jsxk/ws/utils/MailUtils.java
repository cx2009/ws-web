package com.jsxk.ws.utils;


import com.jsxk.ws.model.Po.MailContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailUtils {


    @Autowired
    JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String sendMail;

    public void sendMail(MailContent content) {


        // "<h1>来自******的激活邮件，激活请点击以下链接</h1><h3><a href='"</a></h3>",
        // "text/html;charset=UTF-8");

        try {

            SimpleMailMessage mainMessage = new SimpleMailMessage();

            mainMessage.setFrom(sendMail);

            //接收者
            mainMessage.setTo(content.getSendname());
            //发送的标题
            mainMessage.setSubject(content.getSubject());
            //发送的内容
            mainMessage.setText(content.getContent());
            jms.send(mainMessage);


        } catch (Exception ex) {
            log.error("{发送失败{}}", ex);
        }


    }


}
