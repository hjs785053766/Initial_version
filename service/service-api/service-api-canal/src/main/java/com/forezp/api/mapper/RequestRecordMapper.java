package com.forezp.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.api.entity.RequestRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 请求记录 Mapper 接口
 * </p>
 *
 * @author hjs
 * @since 2020-05-28
 */
public interface RequestRecordMapper extends BaseMapper<RequestRecord> {

    @Insert({"<script> " +
            "insert into `sys_journal`.`request_record`" +
            "        <trim prefix='(' suffix=')' suffixOverrides=','>" +
            "            <if test='id != null'>" +
            "                id," +
            "            </if>" +
            "            <if test='requestorId != null'>" +
            "                requestor_id," +
            "            </if>" +
            "            <if test='requestorName != null'>" +
            "                requestor_name," +
            "            </if>" +
            "            <if test='requestInterface != null'>" +
            "                request_Interface," +
            "            </if>" +
            "            <if test='requestBusinessId != null'>" +
            "                request_business_id," +
            "            </if>" +
            "            <if test='requestTime != null'>" +
            "                request_time," +
            "            </if>" +
            "        </trim>" +
            "        <trim prefix='values (' suffix=')' suffixOverrides=','>" +
            "            <if test='id != null'>" +
            "                #{id,jdbcType=INTEGER}," +
            "            </if>" +
            "            <if test='requestorId != null'>" +
            "                #{requestorId,jdbcType=VARCHAR}," +
            "            </if>" +
            "            <if test='requestorName != null'>" +
            "                #{requestorName,jdbcType=VARCHAR}," +
            "            </if>" +
            "            <if test='requestInterface != null'>" +
            "                #{requestInterface,jdbcType=VARCHAR}," +
            "            </if>" +
            "            <if test='requestBusinessId != null'>" +
            "                #{requestBusinessId,jdbcType=VARCHAR}," +
            "            </if>" +
            "            <if test='requestTime != null'>" +
            "                #{requestTime,jdbcType=TIMESTAMP}," +
            "            </if>" +
            "        </trim>"+
            "</script>"})
    int insertSelective(RequestRecord requestRecord);

    @Select({"<script> " +
            " select" +
            "   id, requestor_id, requestor_name, request_Interface, request_business_id, request_time" +
            " from `sys_journal`.`request_record`" +
            " <where>" +
            "   <if test= 'id != null '> " +
            "       AND id = #{id,jdbcType=INTEGER} " +
            "   </if> " +
            "   <if test= 'requestorId != null '> " +
            "       AND requestor_id = #{requestorId,jdbcType=VARCHAR}" +
            "   </if> " +
            "   <if test= 'requestorName != null '> " +
            "       AND requestor_name = #{requestorName,jdbcType=VARCHAR}" +
            "   </if> " +
            "   <if test= 'requestInterface != null '> " +
            "       AND request_Interface = #{requestInterface,jdbcType=VARCHAR}" +
            "   </if> " +
            "   <if test= 'requestBusinessId != null '> " +
            "       AND request_business_id = #{requestBusinessId,jdbcType=VARCHAR}" +
            "   </if> " +
            "   <if test= 'requestTime != null '> " +
            "       AND request_time = #{requestTime,jdbcType=VARCHAR}" +
            "   </if> " +
            " </where>"+
            "</script>"})
    List<RequestRecord> getRequestRecordList(Map map);
}
