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
    public UserPo getUserInfo(long uid) {
        return userPoMapper.selectByPrimaryKey(uid);
    }
}
