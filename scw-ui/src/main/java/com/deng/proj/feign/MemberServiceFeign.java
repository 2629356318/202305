package com.deng.proj.feign;

import com.deng.proj.config.FeignConfig;
import com.deng.proj.feign.impl.MemberServiceFeignException;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.UserAddressVo;
import com.deng.proj.vo.UserRegistVo;
import com.deng.proj.vo.UserRespVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 10:52
 * @Version 1.0
 */
@FeignClient(value = "SCW-USER",configuration = FeignConfig.class,fallback = MemberServiceFeignException.class)
public interface MemberServiceFeign {
    @PostMapping("/login")
    public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct,
                                         @RequestParam("password") String password);



    @PostMapping("/sendCode")
    public AppResponse<String> sendCode(@RequestParam("loginacct") String loginacct);



    @PostMapping("/regist")
    public AppResponse<String> regist(@RequestBody UserRegistVo userRegistVo);
    /**
     * 根据用户编号，获取用户基本信息
     * @param id
     * @return
     */
    @GetMapping("/user/findUser/{id}")
    public AppResponse<UserRespVo> findUser(@PathVariable("id") Integer id);
    /**
     * 获取当前登录账号的地址  /login/info
     * @param accessToken
     * @return
     */
    @GetMapping("/address")
    public AppResponse<List<UserAddressVo>> address(@RequestParam("accessToken") String accessToken);
}
