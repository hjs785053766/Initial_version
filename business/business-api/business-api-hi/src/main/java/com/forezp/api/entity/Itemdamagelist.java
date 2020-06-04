package com.forezp.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 酒店物品损坏列表
 * </p>
 *
 * @author hjs
 * @since 2020-06-02
 */
@TableName("itemdamagelist")
public class Itemdamagelist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 酒店id
     */
    private Integer hotelId;

    /**
     * 物品描述
     */
    @TableField("Description_of_goods")
    private String descriptionOfGoods;

    /**
     * 费用（分）
     */
    private Long parameter;

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
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
    public String getDescriptionOfGoods() {
        return descriptionOfGoods;
    }

    public void setDescriptionOfGoods(String descriptionOfGoods) {
        this.descriptionOfGoods = descriptionOfGoods;
    }
    public Long getParameter() {
        return parameter;
    }

    public void setParameter(Long parameter) {
        this.parameter = parameter;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Itemdamagelist{" +
        "id=" + id +
        ", hotelId=" + hotelId +
        ", descriptionOfGoods=" + descriptionOfGoods +
        ", parameter=" + parameter +
        ", state=" + state +
        "}";
    }
}
