package com.forezp.api.mapper;

import com.forezp.api.entity.user_info.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.api.entity.user_info.UserInfoTwo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hjs
 * @since 2020-06-04
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfoTwo SignIn(Map map);

}
