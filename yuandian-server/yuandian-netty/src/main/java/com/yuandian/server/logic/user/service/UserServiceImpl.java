package com.yuandian.server.logic.user.service;

import com.yuandian.server.logic.mapper.UserPoMapper;
import com.yuandian.server.logic.model.entity.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserPoMapper userPoMapper;

    @Override
    public long register(UserPo userPo) {
        return 0;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public UserPo getUserInfo(long uid) {
        return userPoMapper.selectByPrimaryKey(uid);
    }

    @Override
    public long update(UserPo po) {
        return 0;
    }
}
