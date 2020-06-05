package com.forezp.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forezp.api.entity.Aunt;
import com.forezp.api.util.Notice;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 清洁阿姨表 服务类
 * </p>
 *
 * @author hjs
 * @since 2020-06-01
 */
public interface AuntService extends IService<Aunt> {

    @PostMapping(value = "/AuntService/updAllRequestRecord")
    Notice updAllRequestRecord(Aunt aunt);
}
