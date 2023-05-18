package com.deng.proj.feign.impl;

import com.deng.proj.feign.MemberServiceFeign;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.UserAddressVo;
import com.deng.proj.vo.UserRegistVo;
import com.deng.proj.vo.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 10:55
 * @Version 1.0
 */
@Slf4j
@Component
public class MemberServiceFeignException implements MemberServiceFeign {
    @Override
    public AppResponse<UserRespVo> login(String loginacct, String password) {
        AppResponse<UserRespVo> fail = AppResponse.fail(null);
        fail.setMsg("调用远程服务器失败【登录】");
        return fail;
    }
    @Override
    public AppResponse<UserRespVo> findUser(Integer id) {
        AppResponse<UserRespVo> fail = AppResponse.fail(null);
        fail.setMsg("调用远程服务器失败【获取用户信息】");
        return fail;
    }

    @Override
    public AppResponse<String> sendCode(String loginacct) {
        AppResponse<String> fail = AppResponse.fail(null);
        fail.setMsg("调用远程服务器失败【发验证码】");
        return fail;
    }
    @Override
    public AppResponse<List<UserAddressVo>> address(String accessToken) {
        AppResponse<List<UserAddressVo>> fail = AppResponse.fail(null);
        fail.setMsg("调用远程服务器失败【获取用户地址列表】");
        return fail;
    }

    @Override
    public AppResponse<String> regist(UserRegistVo userRegistVo) {
        AppResponse<String> fail = AppResponse.fail(null);
        fail.setMsg("调用远程服务器失败【用户注册】");
        return fail;
    }
}