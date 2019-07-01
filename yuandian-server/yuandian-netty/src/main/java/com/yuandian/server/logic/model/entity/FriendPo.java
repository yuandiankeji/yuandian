package com.yuandian.server.logic.model.entity;

import java.util.Date;

public class FriendPo extends FriendPoKey {
    private Integer groupId;

    private Date cTime;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}