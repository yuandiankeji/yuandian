package com.yuandian.server.logic.friends.service;

import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.RedisKeyUtils;
import com.yuandian.core.common.ResultObject;
import com.yuandian.core.utils.ZDateUtils;
import com.yuandian.server.config.RedisFactory;
import com.yuandian.server.core.consts.ApplyConst;
import com.yuandian.server.logic.mapper.FriendPoMapper;
import com.yuandian.server.logic.model.entity.ApplyPo;
import com.yuandian.server.logic.model.entity.FriendPo;
import com.yuandian.server.logic.model.entity.FriendPoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        FriendPo friend = new FriendPo(uid, fuid);
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
        String applyListKey = RedisKeyUtils.getFriendApplyListKey(targetId);
        RedisFactory.Redis redis = RedisFactory.getInstance().getRedis("global");
        boolean exists = redis.hexists(applyListKey, targetId + "");
        if (exists) {
            return -1;
        }
        ApplyPo applyPo = new ApplyPo();
        applyPo.setUid(targetId);
        applyPo.setTargetId(uid);
        applyPo.setOption(ApplyConst.DEFAULT_OPTION);
        applyPo.setcTime(ZDateUtils.now().getTime());
        redis.hset(applyListKey, uid + "", applyPo.serialize());
        return 0;
    }

    @Override
    public boolean applyOption(long uid, long targetId, int option) {
        switch (option) {
            case ApplyConst.APPLY_AGREE:
                break;
            case ApplyConst.REFUSE_APPLY:
                break;
            case ApplyConst.BLACK_APPLY:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public List<ApplyPo> getApplyList(long uid) {
        String applyListKey = RedisKeyUtils.getFriendApplyListKey(uid);
        RedisFactory.Redis redis = RedisFactory.getInstance().getRedis("global");
        Map<String, String> dataMap = redis.hgetAll(applyListKey);
        List<ApplyPo> applyPoList = new ArrayList<>();
        dataMap.forEach((targetUid, applyPoStr) -> {
            ApplyPo applyPo = new ApplyPo();
            applyPo.deserialize(applyPoStr);
            applyPoList.add(applyPo);
        });
        return applyPoList;
    }

    @Override
    public ResultObject<Integer> refuseApply(long uid, long targetId) {
        return null;
    }

    @Override
    public List<Long> getBlacklist(long uid) {
        return null;
    }

    @Override
    public List<Long> addBlackList(long uid, long targetUId) {
        return null;
    }

    @Override
    public void removeBlack(long uid, long targetUid) {

    }
}
