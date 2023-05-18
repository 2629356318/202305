package com.deng.proj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "用户注册视图类")
@Data
public class UserRegistVo {
    @ApiModelProperty("手机号")
    private String loginacct;
    @ApiModelProperty("密码")
    private String userpswd;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("真实姓名")
    private String realname;
    @ApiModelProperty("用户昵称")
    private String username;
}
