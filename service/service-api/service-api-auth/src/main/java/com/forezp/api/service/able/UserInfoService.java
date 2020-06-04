package com.forezp.api.service.able;

import com.forezp.api.entity.user_info.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forezp.api.entity.user_info.UserInfoTwo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hjs
 * @since 2020-06-04
 */
public interface UserInfoService extends IService<UserInfo> {

    UserInfoTwo SignIn(Map map);
}
