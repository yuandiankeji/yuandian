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
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        applyPo.setOption(ApplyConst.DEFAULT_OPTION.getCode());
        applyPo.setcTime(ZDateUtils.now().getTime());
        redis.hset(applyListKey, uid + "", applyPo.serialize());
        return 0;
    }

    /**
     * 好友申请操作
     *
     * @param uid
     * @param targetId
     * @param option
     * @return
     */
    @Override
    public boolean applyOption(long uid, long targetId, int option) {
        String applyListKey = RedisKeyUtils.getFriendApplyListKey(uid);
        RedisFactory.Redis redis = RedisFactory.getInstance().getRedis("global");
        String filed = targetId + "";
        if (redis.hexists(applyListKey, filed)) {
            return false;
        }
        ApplyPo applyPo = new ApplyPo();
        applyPo.setUid(uid);
        applyPo.setTargetId(targetId);
        applyPo.setcTime(ZDateUtils.now().getTime());
        applyPo.setOption(option);
        redis.hset(applyListKey, filed, applyPo.serialize());
        return false;
    }

    /**
     * 申请列表
     * @param uid
     * @return
     */
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

    /**
     * 拒绝好友
     * @param uid
     * @param targetId
     * @return
     */
    @Override
    public ResultObject<Integer> refuseApply(long uid, long targetId) {
        this.applyOption(uid,targetId,ApplyConst.REFUSE_APPLY.getCode());
        return null;
    }

    //黑名单相关
    @Override
    public List<Long> getBlacklist(long uid) {
        String key = RedisKeyUtils.getBlackListKey(uid);
        Set<String> set = RedisFactory.getInstance().getRedis("global").smembersString(key);
        List<Long> blackList = new ArrayList<>();
        set.forEach(targetId -> blackList.add(Long.parseLong(targetId)));
        return blackList;
    }

    @Override
    public List<Long> addBlackList(long uid, long targetUId) {
        String key = RedisKeyUtils.getBlackListKey(uid);
        RedisFactory.getInstance().getRedis("global").saddString(key, targetUId + "");
        return getBlacklist(uid);
    }

    @Override
    public void removeBlack(long uid, long targetUid) {
        String key = RedisKeyUtils.getBlackListKey(uid);
        RedisFactory.getInstance().getRedis("global").sremString(key, targetUid + "");

    }
}
