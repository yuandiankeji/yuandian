package com.yuandian.service.impl;

import com.robert.vesta.service.intf.IdService;
import com.yuandian.entity.UserPO;
import com.yuandian.mapper.UserPOMapper;
import com.yuandian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: luyufeng
 * @Date: 2019/4/23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserPOMapper userPOMapper;

    @Autowired
    private IdService idService;

    @Override
    public UserPO selectUserById(long uid) {
        return userPOMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void updateUser(UserPO userPO) {
        userPOMapper.updateByPrimaryKeySelective(userPO);
    }

    @Override
    public void insertUser(UserPO userPO) {
        userPO.setUid(idService.genId());
        userPOMapper.insertSelective(userPO);
    }


}
