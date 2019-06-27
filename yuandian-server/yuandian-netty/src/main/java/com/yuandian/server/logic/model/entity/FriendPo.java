package com.yuandian.server.logic.model.entity;

import java.util.Date;

public class FriendPo extends FriendPoKey {
    private Long groupId;

    private Date cTime;



    public FriendPo(Long uid, Long fuid) {
        super(uid, fuid);
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}