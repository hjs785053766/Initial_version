package com.forezp.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forezp.api.entity.Administratorextraattribute;
import com.forezp.api.util.Notice;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 管理员额外属性表 服务类
 * </p>
 *
 * @author hjs
 * @since 2020-06-01
 */
public interface AdministratorextraattributeService extends IService<Administratorextraattribute> {

    @PostMapping(value = "/updAllRequestRecord")
    Notice updAllRequestRecord();
}
