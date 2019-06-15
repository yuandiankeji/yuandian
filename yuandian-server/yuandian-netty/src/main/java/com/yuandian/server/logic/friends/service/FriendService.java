package com.yuandian.server.logic.friends.service;

import com.yuandian.server.logic.friends.entity.ApplyPo;
import com.yuandian.server.logic.friends.entity.FriendPo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FriendService {

    public List<FriendPo> getAllFriend(long uid);

    public void AddFriend(FriendPo friendPo);

    public void deleteFriend(long uid, long targetId);

    public FriendPo getFriendById(long uid, long targetId);


    public List<ApplyPo> getApplyList(long uid);


}
