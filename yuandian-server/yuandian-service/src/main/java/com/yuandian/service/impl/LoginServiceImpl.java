package com.yuandian.service.impl;

import com.yuandian.core.annotation.RedisCacheable;
import com.yuandian.core.annotation.RedisInsertable;
import com.yuandian.core.annotation.RedisUpdateable;
import com.yuandian.core.common.Rediskey;
import com.yuandian.core.entity.login.LoginPO;
import com.yuandian.core.enums.RedisDataType;
import com.yuandian.mapper.LoginMapper;
import com.yuandian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/4/6
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @RedisInsertable(key = Rediskey.LOGIN_USER, field = "#loginPO.uid")
    @Override
    public LoginPO insert(LoginPO loginPO) {
        loginMapper.insert(loginPO);
        return loginPO;
    }

    @RedisCacheable(key = Rediskey.LOGIN_USER, field = "#uid")
    @Override
    public LoginPO selectByUid(long uid) {
        return loginMapper.selectByUid(uid);
    }

    @RedisCacheable(key = Rediskey.ALLUSER, type = RedisDataType.LIST)
    @Override
    public List<LoginPO> selectAllUser() {
        return loginMapper.selectAllUser();
    }

    @RedisUpdateable
    public void updateUser() {

    }
}
