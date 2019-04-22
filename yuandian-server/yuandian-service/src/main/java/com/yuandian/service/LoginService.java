package com.yuandian.service;

import com.yuandian.core.entity.login.LoginPO;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/4/6
 */

public interface LoginService {

    LoginPO insert(LoginPO loginPO);

    LoginPO selectByUid(long uid);

    List<LoginPO> selectAllUser();
}
