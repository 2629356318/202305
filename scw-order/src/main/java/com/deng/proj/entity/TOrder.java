package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:12
 * @Version 1.0
 */
@Entity
@Table(name = "t_order")
@Data
@ApiModel(description = "实体类--订单")
public class TOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setMemberid(Integer memberid)
    {
        this.memberid = memberid;
    }

    public Integer getMemberid()
    {
        return memberid;
    }
    public void setProjectid(Integer projectid)
    {
        this.projectid = projectid;
    }

    public Integer getProjectid()
    {
        return projectid;
    }
    public void setReturnid(Integer returnid)
    {
        this.returnid = returnid;
    }

    public Integer getReturnid()
    {
        return returnid;
    }
    public void setOrdernum(String ordernum)
    {
        this.ordernum = ordernum;
    }

    public String getOrdernum()
    {
        return ordernum;
    }
    public void setCreatedate(String createdate)
    {
        this.createdate = createdate;
    }

    public String getCreatedate()
    {
        return createdate;
    }
    public void setMoney(Integer money)
    {
        this.money = money;
    }

    public Integer getMoney()
    {
        return money;
    }
    public void setRtncount(Integer rtncount)
    {
        this.rtncount = rtncount;
    }

    public Integer getRtncount()
    {
        return rtncount;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setInvoice(String invoice)
    {
        this.invoice = invoice;
    }

    public String getInvoice()
    {
        return invoice;
    }
    public void setInvoictitle(String invoictitle)
    {
        this.invoictitle = invoictitle;
    }

    public String getInvoictitle()
    {
        return invoictitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("memberid", getMemberid())
                .append("projectid", getProjectid())
                .append("returnid", getReturnid())
                .append("ordernum", getOrdernum())
                .append("createdate", getCreatedate())
                .append("money", getMoney())
                .append("rtncount", getRtncount())
                .append("status", getStatus())
                .append("address", getAddress())
                .append("invoice", getInvoice())
                .append("invoictitle", getInvoictitle())
                .append("remark", getRemark())
                .toString();
    }

}
