package com.deng.proj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 13:37
 * @Version 1.0
 */
@ApiModel(description = "视图对象--项目属性")
@Data
public class ProjectBaseInfoVo extends BaseVo {

    @ApiModelProperty("项目之前的临时token")
    private String projectToken;// 项目的临时token

    @ApiModelProperty("项目的分类id")
    private List<Integer> typeids; // 项目的分类id

    @ApiModelProperty("项目的标签id")
    private List<Integer> tagids; // 项目的标签id

    @ApiModelProperty("项目名称")
    private String name;// 项目名称

    @ApiModelProperty("项目简介")
    private String remark;// 项目简介

    @ApiModelProperty(value = "筹资金额",example = "0")
    private Integer money;// 筹资金额

    @ApiModelProperty(value = "筹资天数",example = "0")
    private Integer day;// 筹资天数

    @ApiModelProperty("项目头部图片")
    private String headerImage;// 项目头部图片

    @ApiModelProperty("项目详情图片")
    private List<String> detailsImage;// 项目详情图片

    @ApiModelProperty("支持者数量")
    private Integer supporter;// 支持者数量

    @ApiModelProperty("支持金额")
    private Integer supportmoney;// 支持金额
    @ApiModelProperty("众筹成功")
    private String status;
}
