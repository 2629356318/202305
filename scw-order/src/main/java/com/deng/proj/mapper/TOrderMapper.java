package com.deng.proj.mapper;

import java.util.List;
import com.deng.proj.entity.TOrder;
import com.deng.proj.vo.TorderVo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-28
 */

public interface TOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public TOrder selectTOrderById(Integer id);

    /**
     * 查询订单列表
     * 
     * @param tOrder 订单
     * @return 订单集合
     */
    public List<TOrder> selectTOrderList(TOrder tOrder);


    public List<TorderVo> a(Integer id);
    /**
     * 新增订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    public int insertTOrder(TOrder tOrder);

    /**
     * 修改订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    public int updateTOrder(TOrder tOrder);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteTOrderById(Integer id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTOrderByIds(Integer[] ids);
}
