package com.deng.proj.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 16:14
 * @Version 1.0
 */
@Data
public class ProjectReturnVo extends BaseVo implements Serializable {

    private String projectToken;

    private Integer id;

    private Integer projectId;

    private Byte type;

    private Integer supportmoney;

    private String content;

    private Integer signalpurchase;

    private Integer purchase;

    private Integer freight;

    private Byte invoice;

    private Integer rtndate;
}
