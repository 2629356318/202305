package com.deng.proj.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 19:26
 * @Version 1.0
 */
@Data
public class TOrderVo implements Serializable {
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

    // 项目名称
    private String name;
}
