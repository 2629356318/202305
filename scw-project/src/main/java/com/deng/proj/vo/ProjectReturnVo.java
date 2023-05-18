package com.deng.proj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 13:40
 * @Version 1.0
 */
@ApiModel(description = "视图对象--回报表属性")
@Data
public class ProjectReturnVo extends BaseVo {

    private String projectToken;

    @ApiModelProperty(value = "回报类型：0-虚拟回报 1-实物回报",required = true)
    private Byte type;

    @ApiModelProperty(value = "支持金额",required = true,example = "0")
    private Integer supportmoney;

    @ApiModelProperty(value = "回报内容",required = true)
    private String content;

    @ApiModelProperty(value = "单笔限购",required = true,example = "0")
    private Integer signalpurchase;

    @ApiModelProperty(value = "限购数量",required = true,example = "0")
    private Integer purchase;

    @ApiModelProperty(value = "运费",required = true,example = "0")
    private Integer freight;

    @ApiModelProperty(value = "发票 0-不开发票 1-开发票",required = true)
    private Byte invoice;

    @ApiModelProperty(value = "回报时间，天数",required = true,example = "0")
    private Integer rtndate;
}
