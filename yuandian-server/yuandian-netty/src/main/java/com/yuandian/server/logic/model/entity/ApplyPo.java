package com.yuandian.server.logic.model.entity;

import com.alibaba.fastjson.JSON;
import com.yuandian.core.common.RedisCache;

public class ApplyPo extends RedisCache {
    private long uid;
    private long targetId;
    private long cTime;
    private long option;

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

    public long getOption() {
        return option;
    }

    public void setOption(long option) {
        this.option = option;
    }

    @Override
    public String serialize() {
        return JSON.toJSONString(this);
    }

    @Override
    public RedisCache deserialize(String json) {
        ApplyPo po = JSON.parseObject(json, ApplyPo.class);
        this.uid = po.getUid();
        this.targetId = po.getTargetId();
        this.option = po.getOption();
        this.cTime = po.getcTime();
        return po;
    }

    @Override
    public String uniqueKey() {
        return uid + "";
    }
}
