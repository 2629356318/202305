package com.deng.proj.controller;

import com.alibaba.fastjson.JSON;
import com.deng.proj.feign.MemberServiceFeign;
import com.deng.proj.feign.ProjectServiceFeign;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.ProjectVo;
import com.deng.proj.vo.UserRegistVo;
import com.deng.proj.vo.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 10:57
 * @Version 1.0
 */
@Controller
@Slf4j
public class DispatcherController {
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private MemberServiceFeign memberServiceFeign;
    @Autowired
    private ProjectServiceFeign projectServiceFeign;

    @PostMapping("/doLogin")
    public String doLogin(Model model,String loginacct, String password, HttpSession session){
        AppResponse<UserRespVo> userRespVoAppResponse = memberServiceFeign.login(loginacct,password);
        UserRespVo userRespVo = userRespVoAppResponse.getData();
        log.info("用户名：{},密码：{}", loginacct, password);
        log.info("登陆用户数据:{}", userRespVo);
        model.addAttribute("userRespVoAppResponse",userRespVoAppResponse);
        if (userRespVo==null){
return "include/include3";

        }
        session.setAttribute("sessionMember",userRespVo);
        String preUrl = (String)session.getAttribute("preUrl");
        if(StringUtils.isEmpty(preUrl)){
            return "include/include3";
        }else{
            return "include/include3";
        }
    }

    @GetMapping("/")
    public String index(Model model){
       // String projectStr = redisTemplate.opsForValue().get("projectStr");
      //  if(null != projectStr){
         //   model.addAttribute("projectList",JSON.parseArray(projectStr,ProjectVo.class));
       // }else{
            AppResponse<List<ProjectVo>> appResponse = projectServiceFeign.all();
            List<ProjectVo> voList = appResponse.getData();
            redisTemplate.opsForValue().set("projectStr", JSON.toJSONString(voList),1, TimeUnit.HOURS);
            model.addAttribute("projectList",voList);
      //  }
        return "index";
    }

    @PostMapping("/sendCode")
    public AppResponse<String> sendCode1(String loginacct, HttpSession session,Model model){
        AppResponse<String> sendCode = memberServiceFeign.sendCode(loginacct);

      return sendCode;
    }

    @GetMapping("/regist0")
    public String regist0(){
        return "regist";
    }

    @PostMapping("/regist")
    public String regist(UserRegistVo userRegistVo,Model model){
        System.out.println(userRegistVo);
        AppResponse<String> regist = memberServiceFeign.regist(userRegistVo);
        model.addAttribute("regist",regist);
        return "include/include5";
    }

    @GetMapping("/xiugai")
    public String xiugai(){
        return "xiugai";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("sessionMember");
        return "include/include4";
    }
}
