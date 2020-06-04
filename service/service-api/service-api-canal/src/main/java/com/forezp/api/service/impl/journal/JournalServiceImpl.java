package com.forezp.api.service.impl.journal;

import com.forezp.api.entity.Journal;
import com.forezp.api.mapper.JournalMapper;
import com.forezp.api.service.able.JournalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 修改表记录 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-05-28
 */
@Service("JournalServiceImpl")
public class JournalServiceImpl extends ServiceImpl<JournalMapper, Journal> implements JournalService {

}
