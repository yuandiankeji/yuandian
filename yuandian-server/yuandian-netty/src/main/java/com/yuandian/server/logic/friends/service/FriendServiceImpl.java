package com.yuandian.server.logic.friends.service;

import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.ResultObject;
import com.yuandian.core.utils.ZDateUtils;
import com.yuandian.server.logic.mapper.FriendPoMapper;
import com.yuandian.server.logic.model.entity.ApplyPo;
import com.yuandian.server.logic.model.entity.FriendPo;
import com.yuandian.server.logic.model.entity.FriendPoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendPoMapper friendMapper;

    @Override
    public ResultObject<Integer> addFriend(long uid, long fuid) {
        boolean isfriend = this.isFriend(uid, fuid);
        if (isfriend) {
            return new ResultObject<>(ErrorCode.FRIEND_REPEAT_ADD, 1);
        }
        FriendPo friend = new FriendPo(uid,fuid);
        friend.setUid(uid);
        friend.setFuid(fuid);
        friend.setcTime(ZDateUtils.now());
        friend.setGroupId(1L);
        friendMapper.insert(friend);
        return new ResultObject<>(ErrorCode.SYS_SUCCESS, 1);
    }

    @Override
    public List<FriendPo> getFriendList(long uid) {
        return friendMapper.getFriends(uid);
    }

    @Override
    public void deleteFriend(long uid, long fuid) {
        friendMapper.deleteByPrimaryKey(new FriendPoKey(uid, fuid));
    }

    @Override
    public boolean isFriend(long uid, long fuid) {
        FriendPo friend = this.getFriend(uid, fuid);
        return friend != null;
    }

    @Override
    public FriendPo getFriend(long uid, long fuid) {

        List<FriendPo> list = this.getFriendList(uid);
        for (FriendPo po : list) {
            if (po.getFuid() == fuid) {
                return po;
            }
        }
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
