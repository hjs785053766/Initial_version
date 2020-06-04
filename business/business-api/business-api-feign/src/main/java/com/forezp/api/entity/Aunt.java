package com.forezp.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 清洁阿姨表
 * </p>
 *
 * @author hjs
 * @since 2020-06-01
 */
@TableName("aunt")
public class Aunt implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 阿姨姓名
     */
    private String name;

    /**
     * 用户微信小程序id
     */
    private String xcxwechatOpenid;

    /**
     * 用户微信公众号id
     */
    private String wechatOpenid;

    /**
     * 阿姨电话
     */
    private String phone;

    /**
     * 评分
     */
    private Double score;

    /**
     * x轴
     */
    private Float xAxis;

    /**
     * y轴
     */
    private Float yAxis;

    /**
     * 状态（0注销  1正常  2待培训 ）
     */
    private Integer state;

    /**
     * 是否自动接单
     */
    private Integer automatic;

    /**
     * 运维id
     */
    private Integer administratorId;

    /**
     * 身份证正面(弃用)
     */
    @TableField("FrontofIDcard")
    private String FrontofIDcard;

    /**
     * 身份证反面(弃用)
     */
    @TableField("ReverseofIDcard")
    private String ReverseofIDcard;

    /**
     * 银行卡号(弃用)
     */
    @TableField("BankcardNo")
    private String BankcardNo;

    /**
     * 银行卡预留人名(弃用)
     */
    @TableField("Bankcardholder")
    private String Bankcardholder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getXcxwechatOpenid() {
        return xcxwechatOpenid;
    }

    public void setXcxwechatOpenid(String xcxwechatOpenid) {
        this.xcxwechatOpenid = xcxwechatOpenid;
    }
    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
    public Float getxAxis() {
        return xAxis;
    }

    public void setxAxis(Float xAxis) {
        this.xAxis = xAxis;
    }
    public Float getyAxis() {
        return yAxis;
    }

    public void setyAxis(Float yAxis) {
        this.yAxis = yAxis;
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
    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }
    public String getFrontofIDcard() {
        return FrontofIDcard;
    }

    public void setFrontofIDcard(String FrontofIDcard) {
        this.FrontofIDcard = FrontofIDcard;
    }
    public String getReverseofIDcard() {
        return ReverseofIDcard;
    }

    public void setReverseofIDcard(String ReverseofIDcard) {
        this.ReverseofIDcard = ReverseofIDcard;
    }
    public String getBankcardNo() {
        return BankcardNo;
    }

    public void setBankcardNo(String BankcardNo) {
        this.BankcardNo = BankcardNo;
    }
    public String getBankcardholder() {
        return Bankcardholder;
    }

    public void setBankcardholder(String Bankcardholder) {
        this.Bankcardholder = Bankcardholder;
    }

    @Override
    public String toString() {
        return "Aunt{" +
        "id=" + id +
        ", name=" + name +
        ", xcxwechatOpenid=" + xcxwechatOpenid +
        ", wechatOpenid=" + wechatOpenid +
        ", phone=" + phone +
        ", score=" + score +
        ", xAxis=" + xAxis +
        ", yAxis=" + yAxis +
        ", state=" + state +
        ", automatic=" + automatic +
        ", administratorId=" + administratorId +
        ", FrontofIDcard=" + FrontofIDcard +
        ", ReverseofIDcard=" + ReverseofIDcard +
        ", BankcardNo=" + BankcardNo +
        ", Bankcardholder=" + Bankcardholder +
        "}";
    }
}
