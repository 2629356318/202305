package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:13
 * @Version 1.0
 */
@Entity
@Table(name = "t_return")
@Data
@ApiModel(description = "实体类--回报类")
public class TReturn implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer projectid;

    private String type;

    private Integer supportmoney;

    private String content;

    private Integer count;

    private Integer signalpurchase;

    private Integer purchase;

    private Integer freight;

    private String invoice;

    private Integer rtndate;

}
