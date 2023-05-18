package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 10:30
 * @Version 1.0
 */
@Entity
@Table(name = "t_project")
@Data
@ApiModel(description = "项目实体类")
public class TProject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String remark;

    private Integer money;

    private Integer day;

    private String status;

    private String deploydate;

    private Long supportmoney;

    private Integer supporter;

    private Integer completion;

    private Integer memberid;

    private String createdate;

    private Integer follower;


}
