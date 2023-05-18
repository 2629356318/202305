package com.deng.proj.service;

import com.deng.proj.entity.TOrder;
import com.deng.proj.vo.OrderInfoSubmitVo;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 20:29
 * @Version 1.0
 */
public interface OrderService {
    public TOrder save(OrderInfoSubmitVo vo);
    public List<TOrder> findTOrderByberId(Integer memberId);
}
