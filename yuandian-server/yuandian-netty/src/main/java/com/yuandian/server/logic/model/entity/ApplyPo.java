package com.yuandian.server.logic.model.entity;

import com.yuandian.core.common.RedisCache;

public class ApplyPo implements RedisCache {
    private long uid;
    private long targetId;
    private long cTime;
    private  long option;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public long getcTime() {
        return cTime;
    }

    public void setcTime(long cTime) {
        this.cTime = cTime;
    }

    @Override
    public String serialize() {
        return null;
    }

    @Override
    public Object deserialize(String json) {
        return null;
    }
}
