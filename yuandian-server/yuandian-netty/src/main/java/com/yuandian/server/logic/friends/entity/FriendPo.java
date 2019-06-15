package com.yuandian.server.logic.friends.entity;

import com.yuandian.core.common.RedisCache;

public class FriendPo implements RedisCache {
    private Long uid;
    private Long fUid;
    private Long cTime;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getfUid() {
        return fUid;
    }

    public void setfUid(Long fUid) {
        this.fUid = fUid;
    }

    public Long getcTime() {
        return cTime;
    }

    public void setcTime(Long cTime) {
        this.cTime = cTime;
    }

    @Override
    public Object deserialize(String json) {
        return null;
    }

    @Override
    public String serialize() {
        return null;
    }

}
