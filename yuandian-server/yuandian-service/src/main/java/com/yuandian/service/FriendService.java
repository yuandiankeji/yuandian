package com.yuandian.service;

import com.yuandian.entity.FriendPo;

import java.util.List;

public interface FriendService {
    public int addFriend(long uid, long fuid);

    public List<FriendPo> getFriendList(long uid);

    public void deleteFriend(long uid, long fuid);

    public boolean isFriend(long uid, long fuid);

    public FriendPo getFriend(long uid, long fuid);


    public int apply(long uid, long targetId);

    public boolean applyOption(long uid, long targetId);

    public List<Long> getApplyList(long uid);


}
