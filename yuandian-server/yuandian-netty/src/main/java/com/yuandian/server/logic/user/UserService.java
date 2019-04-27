package com.yuandian.server.logic.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户管理
 */
public class UserService {
    /**
     * @param uid
     * @param deviceId
     * @param token
     */
    public static boolean checkUserToken(long uid, String deviceId, String token) {

        return true;
    }

}
