package com.yuandian.server.logic.user;

import com.yuandian.server.core.net.IoClient;
import io.netty.channel.Channel;

/**
 * @author twjitm 2019/4/9/15:53
 */
public class UserInfo extends IoClient {
    private long uid;
    private String openId;
    private String device;


    public UserInfo(Channel channel) {
        super(channel);
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
