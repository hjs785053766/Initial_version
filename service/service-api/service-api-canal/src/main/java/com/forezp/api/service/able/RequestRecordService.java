package com.forezp.api.service.able;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forezp.api.entity.RequestRecord;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 请求记录 服务类
 * </p>
 *
 * @author hjs
 * @since 2020-05-28
 */
public interface RequestRecordService extends IService<RequestRecord> {

    int insertSelective(RequestRecord requestRecord);

    List<RequestRecord> getRequestRecordList(Map map);
}
