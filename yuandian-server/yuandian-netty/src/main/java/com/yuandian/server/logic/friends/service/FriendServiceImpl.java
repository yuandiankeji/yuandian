package com.yuandian.server.logic.friends.service;

import com.yuandian.server.logic.friends.entity.ApplyPo;
import com.yuandian.server.logic.friends.entity.FriendPo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Override
    public List<FriendPo> getAllFriend(long uid) {
        return null;
    }

    @Override
    public void AddFriend(FriendPo friendPo) {

    }

    @Override
    public void deleteFriend(long uid, long targetId) {

    }

    @Override
    public FriendPo getFriendById(long uid, long targetId) {
        return null;
    }

    @Override
    public List<ApplyPo> getApplyList(long uid) {
        return null;
    }
}
