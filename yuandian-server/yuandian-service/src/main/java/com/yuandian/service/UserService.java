package com.yuandian.service;

import com.yuandian.domain.UserPO;

/**
 * @author: luyufeng
 * @Date: 2019/4/23
 */

public interface UserService {
    UserPO selectUserById(long uid);
    void updateUser(UserPO userPO);
    void insertUser(UserPO userPO);
}
