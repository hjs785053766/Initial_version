package com.forezp.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 管理员额外属性表
 * </p>
 *
 * @author hjs
 * @since 2020-06-01
 */
@TableName("administratorextraattribute")
public class Administratorextraattribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员id
     */
    private Integer administratorId;

    /**
     * 管理员额外类型id
     */
    private Integer titleId;

    /**
     * 内容
     */
    private String substance;

    /**
     * 状态
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }
    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }
    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Administratorextraattribute{" +
        "id=" + id +
        ", administratorId=" + administratorId +
        ", titleId=" + titleId +
        ", substance=" + substance +
        ", state=" + state +
        "}";
    }
}
