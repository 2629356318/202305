package com.deng.proj.controller;

import com.deng.proj.entity.TMember;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 18:52
 * @Version 1.0
 */
@Api(tags = "获取会员信息/更新个人信息/获取用户收货地址")
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private MemberService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //根据用户编号获取用户信息
    @GetMapping("/findUser/{id}")
    public AppResponse<TMember> findUser(@PathVariable("id") Integer id) {
        TMember tmember = userService.findTmemberById(id);
        return AppResponse.ok(tmember);
    }
}

