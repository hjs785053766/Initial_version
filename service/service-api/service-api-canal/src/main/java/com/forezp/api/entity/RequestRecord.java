package com.forezp.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 请求记录
 * </p>
 *
 * @author hjs
 * @since 2020-05-28
 */
@TableName("request_record")
public class RequestRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请求人id
     */
    private String requestorId;

    /**
     * 请求人姓名
     */
    private String requestorName;

    /**
     * 请求接口
     */
    @TableField("request_Interface")
    private String requestInterface;

    /**
     * 请求业务id
     */
    private String requestBusinessId;

    /**
     * 请求时间
     */
    private Date requestTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    public String getRequestInterface() {
        return requestInterface;
    }

    public void setRequestInterface(String requestInterface) {
        this.requestInterface = requestInterface;
    }

    public String getRequestBusinessId() {
        return requestBusinessId;
    }

    public void setRequestBusinessId(String requestBusinessId) {
        this.requestBusinessId = requestBusinessId;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "RequestRecord{" +
                "id=" + id +
                ", requestorId=" + requestorId +
                ", requestorName=" + requestorName +
                ", requestInterface=" + requestInterface +
                ", requestBusinessId=" + requestBusinessId +
                ", requestTime=" + requestTime +
                "}";
    }

    Journal journal;

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
