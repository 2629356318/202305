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
@ApiModel(description = "实体类--支付")
@Entity
@Table(name = "t_transaction")
@Data
public class TTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ordersn;

    private String paysn;

    private Integer memberid;

    private Float amount;

    private Byte paystate;

    private String source;

    private Byte status;

    private String completiontime;

    private String note;

    private String createat;

    private String updateat;

}
