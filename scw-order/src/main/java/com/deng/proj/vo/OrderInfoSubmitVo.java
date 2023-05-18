package com.deng.proj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:15
 * @Version 1.0
 */
@Data
@ApiModel(description = "视图对象--订单类")
public class OrderInfoSubmitVo {
    @ApiModelProperty(value = "用户令牌")
    private String accessToken;
    private Integer projectid;//项目ID
    private Integer returnid;//回报ID
    private Integer rtncount;//回报数量
    private String address;//收货地址
    private Byte invoice;//是否开发票 0 - 不开发票， 1 - 开发票
    private String invoictitle;//发票名头
    private String remark;//备注
}
