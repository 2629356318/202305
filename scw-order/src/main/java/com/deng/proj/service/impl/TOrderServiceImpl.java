package com.deng.proj.service.impl;

import java.util.List;

import com.deng.proj.entity.TOrder;
import com.deng.proj.mapper.TOrderMapper;
import com.deng.proj.service.ITOrderService;
import com.deng.proj.vo.TorderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-28
 */
@Service
public class TOrderServiceImpl implements ITOrderService
{
    @Autowired
    private TOrderMapper tOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public TOrder selectTOrderById(Integer id)
    {
        return tOrderMapper.selectTOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param tOrder 订单
     * @return 订单
     */
    @Override
    public List<TOrder> selectTOrderList(TOrder tOrder)
    {
        return tOrderMapper.selectTOrderList(tOrder);
    }


    @Override
    public List<TorderVo> selectTOrderLists(Integer id)
    {
        return tOrderMapper.a(id);
    }
    /**
     * 新增订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    @Override
    public int insertTOrder(TOrder tOrder)
    {
        return tOrderMapper.insertTOrder(tOrder);
    }

    /**
     * 修改订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    @Override
    public int updateTOrder(TOrder tOrder)
    {
        return tOrderMapper.updateTOrder(tOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteTOrderByIds(Integer[] ids)
    {
        return tOrderMapper.deleteTOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteTOrderById(Integer id)
    {
        return tOrderMapper.deleteTOrderById(id);
    }
}
