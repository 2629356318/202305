package com.deng.proj.entity;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_member_address")
@ApiModel(description = "实体类--地址表")
@NoArgsConstructor
@AllArgsConstructor
public class TMemberAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("地址id")
    private Integer id;

    private Integer memberid;
    @ApiModelProperty("会员地址")
    private String address;
}
