package com.yuandian.service;


import com.yuandian.entity.LoginPO;

/**
 * @author: luyufeng
 * @Date: 2019/4/6
 */

public interface LoginService {

    LoginPO insert(LoginPO loginPO);

    LoginPO selectByUid(long uid);

    void update(LoginPO loginPO);

    LoginPO selectByPhone(String phone);

}
