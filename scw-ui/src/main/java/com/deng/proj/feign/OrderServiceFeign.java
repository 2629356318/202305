package com.deng.proj.feign;

import com.deng.proj.config.FeignConfig;
import com.deng.proj.feign.impl.OrderServiceFeignException;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.OrderFormInfoSubmitVo;
import com.deng.proj.vo.TOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 19:27
 * @Version 1.0
 */
@FeignClient(value = "scw-order",fallback = OrderServiceFeignException.class,configuration= FeignConfig.class)
public interface OrderServiceFeign {

    @PostMapping("/order/save")
    AppResponse<TOrderVo> createOrder(@RequestBody OrderFormInfoSubmitVo vo);
    @PostMapping("/order/all")
    AppResponse<List<TOrderVo>> findall(@RequestBody String accessToken);

    @PostMapping("/order/del/{id}")
    AppResponse del(@PathVariable("id") Integer id);
}
