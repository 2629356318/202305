package com.deng.proj.controller;

import com.deng.proj.feign.OrderServiceFeign;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.OrderFormInfoSubmitVo;
import com.deng.proj.vo.ReturnPayConfirmVo;
import com.deng.proj.vo.TOrderVo;
import com.deng.proj.vo.UserRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 19:28
 * @Version 1.0
 */
@Controller
public class OrderController {
    @Autowired
    private OrderServiceFeign orderServiceFeign;

    //保存订单
    @RequestMapping("/order/save")
    public String OrderPay(OrderFormInfoSubmitVo vo, HttpSession session) {
        UserRespVo userResponseVo = (UserRespVo) session.getAttribute("sessionMember");
        if (userResponseVo == null) {
            return "redirect:/login";
        }
        String accessToken = userResponseVo.getAccessToken();
        vo.setAccessToken(accessToken);
        ReturnPayConfirmVo confirmVo = (ReturnPayConfirmVo) session.getAttribute("returnConfirmSession");
        if (confirmVo == null) {
            return "redirect:/login";
        }
        vo.setProjectid(confirmVo.getProjectId());
        vo.setReturnid(confirmVo.getId());
        vo.setRtncount(confirmVo.getNum());
        AppResponse<TOrderVo> order = orderServiceFeign.createOrder(vo);
        TOrderVo data = order.getData();
        return "include/include1";
    }


    @RequestMapping("/order/all")
    public String Return(HttpSession session, Model model) {
        //检查是否登陆，如果没登陆，保存当前路径，并跳转到登陆页面
        UserRespVo userResponseVo = (UserRespVo) session.getAttribute("sessionMember");
        if (userResponseVo == null) {
            return "redirect:/login";
        }
        String accessToken = userResponseVo.getAccessToken();
        AppResponse<List<TOrderVo>> order = orderServiceFeign.findall(accessToken);
        List<TOrderVo> orders = order.getData();
        model.addAttribute("orders",orders);
        return "/member/minecrowdfunding";
    }
    @RequestMapping("/order/del/{id}")
    public String deloreder(@PathVariable("id")Integer id, HttpSession session, Model model) {
        //检查是否登陆，如果没登陆，保存当前路径，并跳转到登陆页面
        UserRespVo userResponseVo = (UserRespVo) session.getAttribute("sessionMember");
        if (userResponseVo == null) {
            return "redirect:/login";
        }

        AppResponse order = orderServiceFeign.del(id);
        return "include/include";

    }

}
