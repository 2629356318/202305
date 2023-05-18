package com.deng.proj.controller;

import com.deng.proj.feign.MemberServiceFeign;
import com.deng.proj.feign.ProjectServiceFeign;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 18:24
 * @Version 1.0
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectServiceFeign projectServiceFeign;
    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @RequestMapping("/projectInfo")
    public String projectInfo(Integer id, Model model, HttpSession session) {
        AppResponse<ProjectDetailVo> vo = projectServiceFeign.detailsInfo(id);
        ProjectDetailVo data = vo.getData();
        model.addAttribute("DetailVo", data);
        session.setAttribute("DetailVo", data);
        return "project/project";
    }

    @RequestMapping("/confirm/returns/{projectId}/{returnId}")
    public String returnInfo(@PathVariable("projectId") Integer projectId, @PathVariable("returnId") Integer returnId, Model model, HttpSession session) {
        //从session获取项目详细信息
        ProjectDetailVo projectDetailVo = (ProjectDetailVo) session.getAttribute("DetailVo");
        //根据项目回报编号，获取项目回报信息
        AppResponse<ReturnPayConfirmVo> appResponse = projectServiceFeign.returnInfo(returnId);
        //获取项目回报信息
        ReturnPayConfirmVo returnPayConfirmVo = appResponse.getData();
        //设置项目回报的项目id
        returnPayConfirmVo.setProjectId(projectId);
        //设置项目回报的项目名称
        returnPayConfirmVo.setProjectName(projectDetailVo.getName());
        //根据项目发起方id，获取项目发起方名称
        AppResponse<UserRespVo> memberServiceAppResponse = memberServiceFeign.findUser(projectDetailVo.getMemberid());
        UserRespVo userRespVo = memberServiceAppResponse.getData();
        //设置发起方名称
        returnPayConfirmVo.setMemberName(userRespVo.getRealname());
        //添加项目回报信息到session
        session.setAttribute("returnConfirm", returnPayConfirmVo);
        //添加项目回报信息到Model
        model.addAttribute("returnConfirm", returnPayConfirmVo);
        return "project/pay-step-1";
    }

    @GetMapping("/confirm/order/{num}")
    public String confirmOrder(@PathVariable("num") Integer num, Model model, HttpSession session) {
        //检查是否登陆，如果没登陆，保存当前路径，并跳转到登陆页面
        UserRespVo userRespVo = (UserRespVo) session.getAttribute("sessionMember");
        if (userRespVo == null) {
            session.setAttribute("preUrl", "project/confirm/order/" + num);
            return "redirect:/login";
        }
        //如果登陆成功，则根据accessToken读取地址列表
        String accessToken = userRespVo.getAccessToken();
        List<UserAddressVo> addresses = memberServiceFeign.address(accessToken).getData();
        model.addAttribute("addresses", addresses);
        //读取回报对象，并设置数量，金额
        ReturnPayConfirmVo returnConfirm = (ReturnPayConfirmVo) session.getAttribute("returnConfirm");
        returnConfirm.setNum(num);
        returnConfirm.setTotalPrice(new BigDecimal(returnConfirm.getSupportmoney() * num + returnConfirm.getFreight()));
        session.setAttribute("returnConfirmSession", returnConfirm);
        return "project/pay-step-2";
    }

    @RequestMapping("/init")
    public String Return1(HttpSession session, Model model) {
        //检查是否登陆，如果没登陆，保存当前路径，并跳转到登陆页面
        UserRespVo userResponseVo = (UserRespVo) session.getAttribute("sessionMember");
        if (userResponseVo == null) {
            return "redirect:/login";
        }
        String accessToken = userResponseVo.getAccessToken();
        BaseVo vo = new BaseVo();
        vo.setAccessToken(accessToken);
        AppResponse<String> vo1 = projectServiceFeign.init(vo);
        ProjectBaseInfoVo aa = new ProjectBaseInfoVo();
        aa.setProjectToken(vo1.getData());
        session.setAttribute("aa", aa);
        model.addAttribute("aa", aa);
        session.setAttribute("vo1", vo1);
        model.addAttribute("vo1", vo1);
        return "include/projection";
    }

    @RequestMapping("/zhuce")
    public String Return3() {
        return "include/tongyi";
    }

    @RequestMapping("/saveBaseInfo")
    public String Chuanjian(HttpSession session, Model model, ProjectBaseInfoVo projectBaseInfoVo) {

        UserRespVo userResponseVo = (UserRespVo) session.getAttribute("sessionMember");

        String accessToken = userResponseVo.getAccessToken();

        projectBaseInfoVo.setAccessToken(accessToken);
        AppResponse<String> vo1 = projectServiceFeign.saveBaseInfo(projectBaseInfoVo);

        session.setAttribute("projectBaseInfoVo", projectBaseInfoVo);
        return "include/torder";
    }

    @RequestMapping("/saveReturn")
    public String Huibao(HttpSession session, Model model, ProjectReturnVo projectReturnVo) {


        List<ProjectReturnVo> aaa = new ArrayList<>();
        aaa.add(projectReturnVo);
        UserRespVo userResponseVo = (UserRespVo) session.getAttribute("sessionMember");

        AppResponse<String> vo1 = projectServiceFeign.saveReturnInfo(aaa);
        session.setAttribute("projectReturnVo", projectReturnVo);
        return "include/include2";
    }

    @RequestMapping("/submit")
    public String Huibao(HttpSession session, Model model) {

        UserRespVo userResponseVo = (UserRespVo) session.getAttribute("sessionMember");
        if (userResponseVo == null) {
            return "redirect:/login";
        }
        String accessToken = userResponseVo.getAccessToken();
        ProjectBaseInfoVo projectBaseInfoVo = (ProjectBaseInfoVo) session.getAttribute("projectBaseInfoVo");
        if (projectBaseInfoVo == null) {
            return "redirect:/login";
        }
        String projectToken  =projectBaseInfoVo.getProjectToken();
        Integer ops =1;
        ProjectReVo projectReVo=new ProjectReVo();
        projectReVo.setProjectToken(projectToken);
        projectReVo.setAccessToken(accessToken);
        projectReVo.setOps(ops);
        AppResponse<Object> vo1 = projectServiceFeign.submit(projectReVo);

        return "index";
    }

}