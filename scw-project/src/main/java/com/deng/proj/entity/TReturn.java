package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;


/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 13:29
 * @Version 1.0
 */
@Entity
@Table(name = "t_return")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "实体类--回报表")
public class TReturn {
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
