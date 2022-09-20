package com.atguigu.emailservice.service.impl;

import com.atguigu.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String email, String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("谷粒学院登录验证码");
        simpleMailMessage.setText("尊敬的：" + email +"您的注册检验验证码为："+ code + " ,有效期5分钟！");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("527638475@qq.com");
        javaMailSender.send(simpleMailMessage);
    }
}
