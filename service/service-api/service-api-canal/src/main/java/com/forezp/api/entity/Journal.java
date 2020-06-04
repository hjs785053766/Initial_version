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
 * 修改表记录
 * </p>
 *
 * @author hjs
 * @since 2020-05-28
 */
@TableName("journal")
public class Journal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请求记录id
     */
    private String requestRecordId;

    /**
     * 业务id
     */
    @TableField("mainBusinessId")
    private String mainBusinessId;

    /**
     * 库名
     */
    private String library;

    /**
     * 表名
     */
    private String surface;

    /**
     * 事件类型
     */
    @TableField("eventType")
    private String eventType;

    /**
     * 时间
     */
    @TableField("executeTime")
    private Date executeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRequestRecordId() {
        return requestRecordId;
    }

    public void setRequestRecordId(String requestRecordId) {
        this.requestRecordId = requestRecordId;
    }
    public String getMainBusinessId() {
        return mainBusinessId;
    }

    public void setMainBusinessId(String mainBusinessId) {
        this.mainBusinessId = mainBusinessId;
    }
    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    @Override
    public String toString() {
        return "Journal{" +
        "id=" + id +
        ", requestRecordId=" + requestRecordId +
        ", mainBusinessId=" + mainBusinessId +
        ", library=" + library +
        ", surface=" + surface +
        ", eventType=" + eventType +
        ", executeTime=" + executeTime +
        "}";
    }
}
