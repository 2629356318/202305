package com.deng.proj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "视图对象--订单类")
public class TorderVo {

    // 项目名称
    private String name;
    private Integer id;

    private Integer memberid;

    private Integer projectid;

    private Integer returnid;

    private String ordernum;

    private String createdate;

    private Integer money;

    private Integer rtncount;

    private String status;

    private String address;

    private String invoice;

    private String invoictitle;

    private String remark;
}
