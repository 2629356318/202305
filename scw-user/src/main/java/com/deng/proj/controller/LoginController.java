package com.deng.proj.controller;

import com.deng.proj.entity.TMember;
import com.deng.proj.entity.TMemberAddress;
import com.deng.proj.enume.ResponseCodeEnume;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.service.MemberService;
import com.deng.proj.utils.SmsTemplate;
import com.deng.proj.vo.UserRegistVo;
import com.deng.proj.vo.UserRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Api(tags = "用户注册，登陆")
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MemberService memberService;
    @Autowired
    private SmsTemplate smsTemplate;

    @PostMapping("/sendCode")
    @ApiOperation(value = "发送验证码")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "loginacct", value = "手机号", required = true, dataTypeClass = String.class)
    )
    public AppResponse<String> sendCode(@RequestParam(value="loginacct",required=false)String loginacct) {
        if(null != loginacct){
        //创建IDi
            int i= (int) (Math.random()*9000+1000);
        String code = String.valueOf(i);
        //缓存code
        redisTemplate.opsForValue().set(loginacct, code);
        //发送
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", loginacct);
        querys.put("param", "code:" + code);
        querys.put("tpl_id", "TP1711063");
        boolean success = smsTemplate.send(querys);
        return success ? AppResponse.ok("验证码发送成功，请注意查收") : AppResponse.fail("短信发送失败！");
    }else {
            return AppResponse.fail("未输入手机号，短信发送失败！");
        }
    }

    @ApiOperation("用户注册")
    @PostMapping("/regist")

    public AppResponse<String> regist(@RequestBody UserRegistVo userRegistVo) {
        if(null != userRegistVo.getLoginacct() && "" != userRegistVo.getLoginacct()) {
            String code = redisTemplate.opsForValue().get(userRegistVo.getLoginacct());
            if (code == null) {
                return AppResponse.fail("验证码不存在");
            }
            if (!code.equals(userRegistVo.getCode())) {
                return AppResponse.fail("验证码错误");
            }
            try {
                //保存会员
                TMember tMember = new TMember();
                BeanUtils.copyProperties(userRegistVo, tMember);
                memberService.registerUser(tMember);
                //删除缓存
                redisTemplate.delete(userRegistVo.getLoginacct());
                return AppResponse.ok("用户操作成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return AppResponse.ok("用户操作成功！");
            }
        }else {
            return AppResponse.fail("手机号不存在");
        }
    }
    @ApiOperation("用户密码修改")
    @PostMapping("/xiugai")

    public AppResponse<String> xiugai(@RequestBody UserRegistVo userRegistVo) {
        if(null!=userRegistVo.getLoginacct()) {
            String code = redisTemplate.opsForValue().get(userRegistVo.getLoginacct());
            if (code == null) {
                return AppResponse.fail("验证码不存在");
            }
            if (!code.equals(userRegistVo.getCode())) {
                return AppResponse.fail("验证码错误");
            }
            try {
                //保存会员
                TMember tMember = new TMember();
                BeanUtils.copyProperties(userRegistVo, tMember);
                memberService.registerUser1(tMember);
                //删除缓存
                redisTemplate.delete(userRegistVo.getLoginacct());
                return AppResponse.ok("用户修改密码成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return AppResponse.ok("用户修改密码成功！");
            }
        }else {
            return AppResponse.fail("手机号不存在");
        }
    }
    /**
     * 用户登陆
     * @param loginacct
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登陆")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "loginacct", value = "用户名", required = true),
                    @ApiImplicitParam(name = "password", value = "密码", required = true)
            }
    )
    public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("password") String password) {
        //验证登陆
        TMember tMember = memberService.login(loginacct, password);
        //如果返回null则登陆失败
        if (tMember == null) {
            log.error("用户不存在");
            AppResponse<UserRespVo> fail = AppResponse.fail(null);
            fail.setMsg("账号密码错误");
            return fail;
        }
        //如果登陆成功，则拷贝tmember 到视图对象
        UserRespVo vo = new UserRespVo();
        BeanUtils.copyProperties(tMember, vo);
        //使用UUID生成一个token值，根据令牌缓存视图对象
        String token = UUID.randomUUID().toString().replace("-", "");
        vo.setAccessToken(token);
        //redis中记录的是token和memberid的对应关系
        redisTemplate.opsForValue().set(token, tMember.getId().toString(), 2, TimeUnit.HOURS);
        AppResponse<UserRespVo> vo1= AppResponse.ok(vo);
        vo1.setMsg("登录成功");
        return vo1;
    }

    /**
     * 获取地址，通过令牌
     * @param accessToken
     * @return
     */
    @GetMapping("/address")
    @ApiOperation("获取用户地址")
    @ApiImplicitParams(@ApiImplicitParam(name = "accessToken", value = "令牌", required = true))
    public AppResponse<Object> address(String accessToken) {
        Integer memberId = Integer.parseInt(redisTemplate.opsForValue().get(accessToken).toString());
        if (memberId == null) {
            return AppResponse.fail("会话不存在");
        }
        List<TMemberAddress> addressList = memberService.findAddressByMemberId(memberId);
        return AppResponse.ok(addressList);
    }
}

