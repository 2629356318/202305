package com.deng.proj.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 13:48
 * @Version 1.0
 */

@Data
public class ProjectVo implements Serializable {

    // 会员id
    private Integer memberid;
    //项目id
    private Integer id;
    // 项目名称
    private String name;
    // 项目简介
    private String remark;
    // 项目头部图片
    private String headerImage;

    private Integer money;
    private String createdate;
    private Integer follower;
}
