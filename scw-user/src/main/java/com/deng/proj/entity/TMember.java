package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "t_member")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "实体类--会员表")
public class TMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "loginacct")
    private String loginacct;
    @Column
    private String userpswd;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String authstatus;
    @Column
    private String usertype;
    @Column
    private String realname;
    @Column
    private String cardnum;
    @Column
    private String accttype;

}
