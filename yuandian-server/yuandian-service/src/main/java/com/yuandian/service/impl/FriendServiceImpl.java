package com.yuandian.service.impl;

import com.yuandian.core.common.ResultObject;
import com.yuandian.entity.ApplyPo;
import com.yuandian.entity.FriendPo;
import com.yuandian.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Override
    public ResultObject<Integer> addFriend(long uid, long fuid) {
        return null;
    }

    @Override
    public List<FriendPo> getFriendList(long uid) {
        return null;
    }

    @Override
    public void deleteFriend(long uid, long fuid) {

    }

    @Override
    public boolean isFriend(long uid, long fuid) {
        return false;
    }

    @Override
    public FriendPo getFriend(long uid, long fuid) {

        return null;
    }

    @Override
    public int apply(long uid, long targetId) {
        return 0;
    }

    @Override
    public boolean applyOption(long uid, long targetId) {
        return false;
    }

    @Override
    public List<ApplyPo> getApplyList(long uid) {
        return null;
    }

    @Override
    public ResultObject<Integer> refuseApply(long uid, long targetId) {
        return null;
    }
}
