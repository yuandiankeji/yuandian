package com.yuandian.server.logic.friends.service;

import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.RedisKeyUtils;
import com.yuandian.core.common.ResultObject;
import com.yuandian.core.utils.ZDateUtils;
import com.yuandian.server.config.RedisService;
import com.yuandian.server.core.consts.ApplyConst;
import com.yuandian.server.logic.mapper.FriendPoMapper;
import com.yuandian.server.logic.model.entity.ApplyPo;
import com.yuandian.server.logic.model.entity.FriendPo;
import com.yuandian.server.logic.model.entity.FriendPoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendPoMapper friendMapper;
    @Autowired
    RedisService redisChatService;
    @Autowired
    RedisService redisGlobalService;

    @Override
    public ResultObject<Integer> addFriend(long uid, long fuid) {
        boolean isfriend = this.isFriend(uid, fuid);
        if (isfriend) {
            return new ResultObject<>(ErrorCode.FRIEND_REPEAT_ADD, 1);
        }
        FriendPo friend = new FriendPo(uid, fuid);
        friend.setUid(uid);
        friend.setFuid(fuid);
        friend.setcTime(new Date());
        friend.setGroupId(1L);
        friendMapper.insert(friend);
        return new ResultObject<>(ErrorCode.SYS_SUCCESS, 1);
    }

    @Override
    public List<Long> getFriendList(long uid) {
        List<FriendPo> list = friendMapper.getFriends(uid);
        Set<Long> friendSet = new HashSet<>();
        list.forEach(friendPo -> {
            if (friendPo.getUid() != uid) {
                friendSet.add(friendPo.getUid());
            }
            if (friendPo.getFuid() != uid) {
                friendSet.add(friendPo.getFuid());
            }
        });
        return new ArrayList<>(friendSet);
    }

    @Override
    public void deleteFriend(long uid, long fuid) {
        friendMapper.deleteByPrimaryKey(new FriendPoKey(uid, fuid));
    }

    @Override
    public boolean isFriend(long uid, long fuid) {
        FriendPo friend = this.getFriend(uid, fuid);
        if (friend == null) {
            friend = this.getFriend(fuid, uid);
        }
        return friend != null;
    }

    @Override
    public FriendPo getFriend(long uid, long fuid) {
        FriendPo po = friendMapper.selectByPrimaryKey(new FriendPoKey(uid, fuid));
        return po;
    }

    @Override
    public int apply(long uid, long targetId) {
        String applyListKey = RedisKeyUtils.getFriendApplyListKey(targetId);
        boolean exists = redisChatService.hexists(applyListKey, targetId + "");
        if (exists) {
            return -1;
        }
        ApplyPo applyPo = new ApplyPo();
        applyPo.setUid(targetId);
        applyPo.setTargetId(uid);
        applyPo.setOption(ApplyConst.DEFAULT_OPTION.getCode());
        applyPo.setcTime(ZDateUtils.getNow());
        redisChatService.hset(applyListKey, uid + "", applyPo.serialize());
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
        String filed = targetId + "";
        if (redisChatService.hexists(applyListKey, filed)) {
            return false;
        }
        if (option == ApplyConst.DEFAULT_OPTION.getCode()) {
            ApplyPo applyPo = new ApplyPo();
            applyPo.setUid(uid);
            applyPo.setTargetId(targetId);
            applyPo.setcTime(ZDateUtils.getNow());
            applyPo.setOption(option);
            redisChatService.hset(applyListKey, filed, applyPo.serialize());
        } else {
            ApplyPo apply = this.getApplyPo(uid, targetId);
            if (apply != null) {
                apply.setOption(option);
                redisChatService.hset(applyListKey, filed, apply.serialize());
            }
        }
        return false;
    }

    /**
     * 申请列表
     *
     * @param uid
     * @return
     */
    @Override
    public List<ApplyPo> getApplyList(long uid) {
        String applyListKey = RedisKeyUtils.getFriendApplyListKey(uid);
        Map<String, String> dataMap = redisChatService.hgetAll(applyListKey);
        List<ApplyPo> applyPoList = new ArrayList<>();
        dataMap.forEach((targetUid, applyPoStr) -> {
            ApplyPo applyPo = new ApplyPo();
            applyPo.deserialize(applyPoStr);
            applyPoList.add(applyPo);
        });
        return applyPoList;
    }

    @Override
    public ApplyPo getApplyPo(long uid, long targetId) {
        List<ApplyPo> applys = this.getApplyList(uid);
        Optional<ApplyPo> first = applys.stream().
                filter(applyPo -> applyPo.getTargetId() == targetId).findFirst();
        return first.orElse(null);
    }

    /**
     * 拒绝好友
     *
     * @param uid
     * @param targetId
     * @return
     */
    @Override
    public ResultObject<Integer> refuseApply(long uid, long targetId) {
        this.applyOption(uid, targetId, ApplyConst.REFUSE_APPLY.getCode());
        return null;
    }

    //黑名单相关
    @Override
    public List<Long> getBlacklist(long uid) {
        String key = RedisKeyUtils.getBlackListKey(uid);
        Set<String> set = redisChatService.smembersString(key);
        List<Long> blackList = new ArrayList<>();
        set.forEach(targetId -> blackList.add(Long.parseLong(targetId)));
        return blackList;
    }

    @Override
    public List<Long> addBlackList(long uid, long targetId) {
        String key = RedisKeyUtils.getBlackListKey(uid);
        redisChatService.saddString(key, targetId + "");
        this.applyOption(uid, targetId, ApplyConst.BLACK_APPLY.getCode());
        return getBlacklist(uid);
    }

    @Override
    public void removeBlack(long uid, long targetUid) {
        String key = RedisKeyUtils.getBlackListKey(uid);
        redisChatService.sremString(key, targetUid + "");
        String applyKey = RedisKeyUtils.getFriendApplyListKey(uid);
        redisChatService.hdel(applyKey, targetUid + "");
    }

    @Override
    public boolean isban(long uid, long targetId) {
        List<Long> bans = this.getBlacklist(uid);
        return bans.stream().anyMatch(banId -> banId.equals(targetId));
    }
}
