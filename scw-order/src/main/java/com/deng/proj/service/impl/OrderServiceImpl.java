package com.deng.proj.service.impl;

import com.deng.proj.entity.TOrder;
import com.deng.proj.entity.TReturn;
import com.deng.proj.enume.OrderStatusEnume;
import com.deng.proj.feign.ProjectFeignService;
import com.deng.proj.repo.OrderRepository;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.service.OrderService;
import com.deng.proj.utils.AppDateUtil;
import com.deng.proj.vo.OrderInfoSubmitVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 20:29
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectFeignService projectFeignService;
    @Autowired
    private OrderRepository orderRepository;

    /**
     * 保存订单
     * @param vo
     * @return
     */
    @Override
    public TOrder save(OrderInfoSubmitVo vo) {
        //获取令牌
        String accessToken = vo.getAccessToken();
        String memberId = stringRedisTemplate.opsForValue().get(accessToken);
        TOrder tOrder = new TOrder();
        //将vo转换成订单对象
        BeanUtils.copyProperties(vo,tOrder);
        tOrder.setMemberid(Integer.parseInt(memberId));
        tOrder.setOrdernum(UUID.randomUUID().toString().replace("-", ""));
        tOrder.setCreatedate(AppDateUtil.getFormatTime());
        tOrder.setStatus(OrderStatusEnume.UNPAY.getCode() + "");

        List<TReturn> returnList = projectFeignService.detailsReturn(vo.getProjectid()).getData();
        for (TReturn tReturn : returnList) {
            if (tReturn.getId().equals(vo.getReturnid())) {
                BeanUtils.copyProperties(tReturn, tOrder);
                tOrder.setId(null);
                //金额=单价*数量+运费
                tOrder.setMoney(tReturn.getSupportmoney() * vo.getRtncount() + tReturn.getFreight());
                orderRepository.save(tOrder);
                return tOrder;
            }
        }
        return null;
    }
    @Override
    public List<TOrder> findTOrderByberId(Integer memberId) {
        List<TOrder> a=orderRepository.findTOrderByberId(memberId);
        return a;
    }
}
