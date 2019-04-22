package com.yuandian.core.entity.login;

import lombok.Data;

/**
 * @author: luyufeng
 * @Date: 2019/4/5
 */

public class LoginPO {

    private long id;

    private long uid;

    private String token;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
