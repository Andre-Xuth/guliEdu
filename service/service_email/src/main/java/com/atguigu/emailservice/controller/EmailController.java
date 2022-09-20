package com.atguigu.emailservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.emailservice.service.EmailService;
import com.atguigu.emailservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Api(description = "邮件组件")
@RestController
@RequestMapping("/eduemail/email")
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送邮件的方法
    @ApiOperation("发送验证码邮件")
    @GetMapping("sendEmail/{email}")
    public R sendEmailCode(@PathVariable String email) {
        //1、从redis中获取验证码，如果获取到,返回ok。表示已经发送过验证码。
        String code = redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2、从redis中获取不到验证码，那么向邮件发送验证码
        //生成6位的验证码随机数
        code = RandomUtil.getSixBitRandom();
        //调用service方法，向指定邮箱发送验证码邮件
        emailService.sendEmail(email, code);
        //邮件发送成功，把发送成功验证码放到redis里面，设置过期时间为5分钟
        redisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
        return R.ok().message("邮件发送成功");
    }

}
