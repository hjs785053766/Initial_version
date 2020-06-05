package com.forezp.api.service;

import com.forezp.api.entity.Business;
import com.forezp.api.util.Notice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 商家表 服务类
 * </p>
 *
 * @author hjs
 * @since 2020-06-02
 */
public interface BusinessService{

    @PostMapping(value = "/BusinessService/updAllRequestRecord")
    Notice updAllRequestRecord(@RequestBody Business business);
}
