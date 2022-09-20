package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
@Api(description = "用户注册登录组件")
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //手机号登录
    @ApiOperation("用户手机登录")
    @PostMapping("login")
    public R loginUser(@ApiParam("用户个人信息") @RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //邮箱登录
    @ApiOperation("用户邮箱登录")
    @PostMapping("loginEmail")
    public R loginEmail(@ApiParam("用户个人信息") @RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.loginEmail(member);
        return R.ok().data("token",token);
    }

    //手机号注册
    @ApiOperation("用户手机注册")
    @PostMapping("register")
    public R registerUser(@ApiParam("用户注册的Vo对象") @RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //手机号注册
    @ApiOperation("用户邮箱注册")
    @PostMapping("registerEmail")
    public R registerEmail(@ApiParam("用户注册的Vo对象") @RequestBody RegisterVo registerVo) {
        memberService.registerEmail(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
}

