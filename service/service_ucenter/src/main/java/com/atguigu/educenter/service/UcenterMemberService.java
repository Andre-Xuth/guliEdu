package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    // 手机登录的方法
    String login(UcenterMember member);

    // 手机注册的方法
    void register(RegisterVo registerVo);

    // 邮箱登录的方法
    String loginEmail(UcenterMember member);

    // 邮箱注册的方法
    void registerEmail(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);
}
