package com.yuandian.service;

import com.yuandian.core.common.ResultObject;
import com.yuandian.entity.ApplyPo;
import com.yuandian.entity.FriendPo;

import java.util.List;

public interface FriendService {
    public ResultObject<Integer> addFriend(long uid, long fuid);

    public List<FriendPo> getFriendList(long uid);

    public void deleteFriend(long uid, long fuid);

    public boolean isFriend(long uid, long fuid);

    public FriendPo getFriend(long uid, long fuid);


    public int apply(long uid, long targetId);

    public boolean applyOption(long uid, long targetId);

    public List<ApplyPo> getApplyList(long uid);


    ResultObject<Integer> refuseApply(long uid, long targetId);

}
