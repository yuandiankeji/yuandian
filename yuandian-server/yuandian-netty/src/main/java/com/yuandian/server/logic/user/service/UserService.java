package com.yuandian.server.logic.user.service;

import com.yuandian.server.logic.model.entity.UserPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户管理
 */
public interface UserService {

    public long register(UserPo userPo);

    public boolean login();

    public boolean logout();

    public UserPo getUserInfo(long uid);

    public long update(UserPo po);

}
