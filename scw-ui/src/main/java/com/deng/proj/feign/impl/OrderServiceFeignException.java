package com.deng.proj.feign.impl;

import com.deng.proj.feign.OrderServiceFeign;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.OrderFormInfoSubmitVo;
import com.deng.proj.vo.TOrderVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @Author by DHF
* @Date 2021/12/2021/12/24 19:28
* @Version 1.0
*/
@Component
public class OrderServiceFeignException implements OrderServiceFeign {
    @Override
    public AppResponse<TOrderVo> createOrder(OrderFormInfoSubmitVo vo) {
        AppResponse<TOrderVo> response = AppResponse.fail(null);
        response.setMsg("远程调用失败【订单】");
        return response;
    }
    @Override
    public AppResponse<List<TOrderVo>> findall(String accessToken) {
        AppResponse<List<TOrderVo>> response = AppResponse.fail(null);
        response.setMsg("远程调用失败【订单】");
        return response;
    }

    @Override
    public AppResponse del(Integer id) {
        AppResponse response = AppResponse.ok(1);
        response.setMsg("删除成功");
        return response;
    }
}
