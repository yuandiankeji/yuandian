package com.yuandian.service.impl;

import com.yuandian.entity.FriendPo;
import com.yuandian.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Override
    public int addFriend(long uid, long fuid) {
        return 0;
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
}
