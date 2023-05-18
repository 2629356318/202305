package com.deng.proj.controller;

import com.deng.proj.entity.TOrder;
import com.deng.proj.entity.TReturn;
import com.deng.proj.feign.ProjectFeignService;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.service.ITOrderService;
import com.deng.proj.service.OrderService;
import com.deng.proj.service.impl.TOrderServiceImpl;
import com.deng.proj.vo.OrderInfoSubmitVo;
import com.deng.proj.vo.TorderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:35
 * @Version 1.0
 */
@Api("订单控制器")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectFeignService projectFeignService;
    @Autowired
    private  ITOrderService itOrderService;

    @ApiOperation("根据项目id，查询回报列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "projectId", value = "项目id", dataTypeClass = Integer.class)
    })
    @GetMapping("/detailReturn/{projectId}")
    public AppResponse<List<TReturn>> detailReturn(@PathVariable("projectId") Integer projectId) {
        return projectFeignService.detailsReturn(projectId);
    }

    @PostMapping("/save")
    @ApiOperation("保存订单")
    public AppResponse<TOrder> save(@RequestBody OrderInfoSubmitVo vo) {
        TOrder order = orderService.save(vo);
        if (order != null) {
            return AppResponse.ok(order);
        } else {
            return AppResponse.fail(null);
        }
    }

    @ApiOperation("根据用户id，查询回报列表")
    @PostMapping("/all")
    @ApiImplicitParams(@ApiImplicitParam(name = "accessToken", value = "令牌", required = true))
    public AppResponse<List<TorderVo>> Return(@RequestBody String accessToken) {
        if (null != accessToken) {
            Integer memberId = Integer.parseInt(redisTemplate.opsForValue().get(accessToken).toString());

            List<TorderVo> tOrders = itOrderService.selectTOrderLists(memberId);
            System.out.println(tOrders);
            if (tOrders != null) {
                return AppResponse.ok(tOrders);
            } else {
                return AppResponse.fail(null);
            }
        } else {
            return AppResponse.fail(null);
        }
    }

    @ApiOperation("根据订单id，删除订单列表")
    @PostMapping("/del/{id}")
    @ApiImplicitParams(@ApiImplicitParam(name = "订单id", value = "id", required = true))
    public AppResponse Return1(@PathVariable("id") Integer id) {

      int a= itOrderService.deleteTOrderById(id);
      return AppResponse.ok("删除成功");
        }


    }






