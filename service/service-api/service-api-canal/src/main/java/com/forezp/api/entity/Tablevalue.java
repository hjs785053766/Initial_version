package com.forezp.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hjs
 * @since 2020-05-28
 */
@TableName("tablevalue")
public class Tablevalue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型（0、前数据，1、后数据）
     */
    private Integer type;

    /**
     * 修改表记录id
     */
    private String journalId;

    /**
     * 值名
     */
    @TableField("valueName")
    private String valueName;

    /**
     * 值
     */
    private String value;

    /**
     * 值类型
     */
    @TableField("valueType")
    private String valueType;

    /**
     * 是否修改
     */
    private String modify = "false";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getModify() {
        return modify;
    }

    public void setModify(String modify) {
        this.modify = modify;
    }

    @Override
    public String toString() {
        return "Tablevalue{" +
                "id=" + id +
                ", type=" + type +
                ", journalId=" + journalId +
                ", valueName=" + valueName +
                ", value=" + value +
                ", valueType=" + valueType +
                ", modify=" + modify +
                "}";
    }
}
