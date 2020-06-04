package com.forezp.api.service.impl.request_record;

import com.forezp.api.entity.RequestRecord;
import com.forezp.api.mapper.RequestRecordMapper;
import com.forezp.api.service.able.RequestRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 请求记录 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-05-28
 */
@Service
public class RequestRecordServiceImpl extends ServiceImpl<RequestRecordMapper, RequestRecord> implements RequestRecordService {


    @Override
    public int insertSelective(RequestRecord requestRecord) {
        return baseMapper.insertSelective(requestRecord);
    }

    @Override
    public List<RequestRecord> getRequestRecordList(Map map) {
        return baseMapper.getRequestRecordList(map);
    }
}
