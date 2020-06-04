package com.forezp.api.service.impl.user_info;

import com.forezp.api.entity.user_info.UserInfo;
import com.forezp.api.entity.user_info.UserInfoTwo;
import com.forezp.api.mapper.UserInfoMapper;
import com.forezp.api.service.able.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-06-04
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public UserInfoTwo SignIn(Map map) {
        return baseMapper.SignIn(map);
    }
}
