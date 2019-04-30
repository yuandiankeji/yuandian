package com.yuandian.service.impl;

import com.yuandian.core.annotation.RedisCacheable;
import com.yuandian.core.annotation.RedisInsertable;
import com.yuandian.core.common.Rediskey;
import com.yuandian.entity.LoginPO;
import com.yuandian.mapper.LoginPOMapper;
import com.yuandian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author: luyufeng
 * @Date: 2019/4/6
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginPOMapper loginPOMapper;

    @RedisInsertable(key = Rediskey.LOGIN_USER, field = "#loginPO.uid")
    @Override
    public LoginPO insert(LoginPO loginPO) {
        loginPOMapper.insert(loginPO);
        return loginPO;
    }

    @RedisCacheable(key = Rediskey.LOGIN_USER, field = "#uid")
    @Override
    public LoginPO selectByUid(long uid) {
        return loginPOMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void update(LoginPO loginPO) {
        loginPOMapper.updateByPrimaryKeySelective(loginPO);
    }


}
