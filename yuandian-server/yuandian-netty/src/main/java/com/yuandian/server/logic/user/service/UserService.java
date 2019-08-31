package com.yuandian.server.logic.user.service;

import com.yuandian.server.logic.model.entity.UserPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户管理
 */
public interface UserService {

    public UserPo getUserInfo(long uid);

    public void logout(long uid);

}
