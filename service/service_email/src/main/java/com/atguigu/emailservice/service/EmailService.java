package com.atguigu.emailservice.service;

public interface EmailService {
    //发送邮件验证码信息
    void sendEmail(String email, String code);
}
