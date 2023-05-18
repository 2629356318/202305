package com.deng.proj.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单提交信息
 * @Author by DHF
 * @Date 2021/12/2021/12/24 19:26
 * @Version 1.0
 */
@Data
public class OrderFormInfoSubmitVo implements Serializable {

    //收货地址id
    private String address;
    // 0代表不开发票  1-代表开发票
    private Byte invoice;
    //发票抬头
    private String invoictitle;
    //订单的备注
    private String remark;

    private Integer rtncount;
    private String accessToken;
    private Integer projectid;
    private Integer returnid;
}