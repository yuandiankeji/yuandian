package com.yuandian.server.logic.model.entity;

public class FriendPoKey {
    private Long uid;

    private Long fuid;

    public FriendPoKey(Long uid, Long fuid) {
        this.uid = uid;
        this.fuid = fuid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getFuid() {
        return fuid;
    }

    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }
}