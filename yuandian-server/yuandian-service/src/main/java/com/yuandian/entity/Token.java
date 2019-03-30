package com.yuandian.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: luyufeng
 * @Date: 2019/3/25
 */
@Getter
@Setter
public class Token {
    //用户id
    private long uid;
    //设备号
    private String deviceId;
    //随机号
    private String token;

    public Token(long uid, String deviceId, String token) {
        this.uid = uid;
        this.deviceId = deviceId;
        this.token = token;
    }
}
