package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 10:30
 * @Version 1.0
 */
@Entity
@Table(name = "t_project")
@Data
@ApiModel(description = "项目实体类")
public class TProject  implements Serializable {
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

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setMoney(Integer money)
    {
        this.money = money;
    }

    public Integer getMoney()
    {
        return money;
    }
    public void setDay(Integer day)
    {
        this.day = day;
    }

    public Integer getDay()
    {
        return day;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setDeploydate(String deploydate)
    {
        this.deploydate = deploydate;
    }

    public String getDeploydate()
    {
        return deploydate;
    }
    public void setSupportmoney(Long supportmoney)
    {
        this.supportmoney = supportmoney;
    }

    public Long getSupportmoney()
    {
        return supportmoney;
    }
    public void setSupporter(Integer supporter)
    {
        this.supporter = supporter;
    }

    public Integer getSupporter()
    {
        return supporter;
    }
    public void setCompletion(Integer completion)
    {
        this.completion = completion;
    }

    public Integer getCompletion()
    {
        return completion;
    }
    public void setMemberid(Integer memberid)
    {
        this.memberid = memberid;
    }

    public Integer getMemberid()
    {
        return memberid;
    }
    public void setCreatedate(String createdate)
    {
        this.createdate = createdate;
    }

    public String getCreatedate()
    {
        return createdate;
    }
    public void setFollower(Integer follower)
    {
        this.follower = follower;
    }

    public Integer getFollower()
    {
        return follower;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("remark", getRemark())
                .append("money", getMoney())
                .append("day", getDay())
                .append("status", getStatus())
                .append("deploydate", getDeploydate())
                .append("supportmoney", getSupportmoney())
                .append("supporter", getSupporter())
                .append("completion", getCompletion())
                .append("memberid", getMemberid())
                .append("createdate", getCreatedate())
                .append("follower", getFollower())
                .toString();
    }
}
