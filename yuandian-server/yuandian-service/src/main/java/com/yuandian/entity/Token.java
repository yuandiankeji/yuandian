package com.yuandian.entity;

import com.yuandian.core.common.Constants;
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
    private long token;

    public Token(long uid, String deviceId, long token) {
        this.uid = uid;
        this.deviceId = deviceId;
        this.token = token;
    }

    public Token() {
    }
    public Token(String auth) {
        String[] params = auth.split(Constants.hash);
        this.uid = Long.valueOf(params[0]);
        this.deviceId = params[1];
        this.token = Long.valueOf(params[2]);
    }

    public String getTokenStr() {
        return deviceId + Constants.hash + token;
    }
}
