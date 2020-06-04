package com.forezp.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 商家表
 * </p>
 *
 * @author hjs
 * @since 2020-06-02
 */
@TableName("business")
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信公众号id
     */
    private String wechatPublicOpenid;

    /**
     * 微信小程序id
     */
    private String wechatProceduresOpenid;

    /**
     * 商家姓名
     */
    private String name;

    /**
     * 商家电话
     */
    private String phone;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 是否自动接单
     */
    private Integer automatic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getWechatPublicOpenid() {
        return wechatPublicOpenid;
    }

    public void setWechatPublicOpenid(String wechatPublicOpenid) {
        this.wechatPublicOpenid = wechatPublicOpenid;
    }
    public String getWechatProceduresOpenid() {
        return wechatProceduresOpenid;
    }

    public void setWechatProceduresOpenid(String wechatProceduresOpenid) {
        this.wechatProceduresOpenid = wechatProceduresOpenid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getAutomatic() {
        return automatic;
    }

    public void setAutomatic(Integer automatic) {
        this.automatic = automatic;
    }

    @Override
    public String toString() {
        return "Business{" +
        "id=" + id +
        ", wechatPublicOpenid=" + wechatPublicOpenid +
        ", wechatProceduresOpenid=" + wechatProceduresOpenid +
        ", name=" + name +
        ", phone=" + phone +
        ", state=" + state +
        ", automatic=" + automatic +
        "}";
    }
}
