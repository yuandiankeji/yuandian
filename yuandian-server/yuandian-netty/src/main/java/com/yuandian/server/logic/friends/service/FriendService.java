package com.yuandian.server.logic.friends.service;

import com.yuandian.core.common.ResultObject;
import com.yuandian.server.core.consts.ApplyConst;
import com.yuandian.server.logic.model.entity.ApplyPo;
import com.yuandian.server.logic.model.entity.FriendPo;


import java.util.List;

public interface FriendService {
    public ResultObject<Integer> addFriend(long uid, long fuid);

    public List<FriendPo> getFriendList(long uid);

    public void deleteFriend(long uid, long fuid);

    public boolean isFriend(long uid, long fuid);

    public FriendPo getFriend(long uid, long fuid);


    public int apply(long uid, long targetId);

    public boolean applyOption(long uid, long targetId, int option);

    public List<ApplyPo> getApplyList(long uid);
    public ApplyPo getApplyPo(long uid,long targetId);


    ResultObject<Integer> refuseApply(long uid, long targetId);

    public List<Long> getBlacklist(long uid);

    public List<Long> addBlackList(long uid, long targetUId);

    public void removeBlack(long uid, long targetUid);

    public boolean isban(long uid, long targetId);

}
