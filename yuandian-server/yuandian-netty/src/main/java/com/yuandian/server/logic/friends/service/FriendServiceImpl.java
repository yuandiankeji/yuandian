package com.yuandian.server.logic.friends.service;

import com.alibaba.fastjson.JSON;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.RedisKeyUtils;
import com.yuandian.core.common.ResultObject;
import com.yuandian.core.utils.ZDateUtils;
import com.yuandian.core.utils.ZStringUtil;
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
    RedisService redisService;

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
        String friendKey = RedisKeyUtils.getFriendListKey(uid);
        redisService.hset(friendKey, uid + "", JSON.toJSONString(friend));
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
        String key = RedisKeyUtils.getFriendListKey(uid);
        redisService.hdel(key, fuid + "");
        String fkey = RedisKeyUtils.getFriendListKey(fuid);
        redisService.hdel(fkey, uid + "");
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

        String friendKey = RedisKeyUtils.getFriendListKey(uid);
        String friendStr = redisService.hget(friendKey, fuid + "");
        FriendPo po;
        if (ZStringUtil.isEmptyStr(friendStr)) {
            po = friendMapper.selectByPrimaryKey(new FriendPoKey(uid, fuid));
            if (po != null) {
                redisService.hset(friendKey, fuid + "", JSON.toJSONString(po));
            }
        } else {
            po = JSON.parseObject(friendStr, FriendPo.class);
        }

        return po;
    }

    @Override
    public int apply(long uid, long targetId) {
        String applyListKey = RedisKeyUtils.getFriendApplyListKey(targetId);
        boolean exists = redisService.hexists(applyListKey, targetId + "");
        if (exists) {
            return -1;
        }
        ApplyPo applyPo = new ApplyPo();
        applyPo.setUid(targetId);
        applyPo.setTargetId(uid);
        applyPo.setOption(ApplyConst.DEFAULT_OPTION.getCode());
        applyPo.setcTime(ZDateUtils.getNow());
        redisService.hset(applyListKey, uid + "", applyPo.serialize());
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
        if (redisService.hexists(applyListKey, filed)) {
            return false;
        }
        if (option == ApplyConst.DEFAULT_OPTION.getCode()) {
            ApplyPo applyPo = new ApplyPo();
            applyPo.setUid(uid);
            applyPo.setTargetId(targetId);
            applyPo.setcTime(ZDateUtils.getNow());
            applyPo.setOption(option);
            redisService.hset(applyListKey, filed, applyPo.serialize());
        } else {
            ApplyPo apply = this.getApplyPo(uid, targetId);
            if (apply != null) {
                apply.setOption(option);
                redisService.hset(applyListKey, filed, apply.serialize());
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
        Map<String, String> dataMap = redisService.hgetAll(applyListKey);
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
        Set<String> set = redisService.smembersString(key);
        List<Long> blackList = new ArrayList<>();
        set.forEach(targetId -> blackList.add(Long.parseLong(targetId)));
        return blackList;
    }

    @Override
    public List<Long> addBlackList(long uid, long targetId) {
        String key = RedisKeyUtils.getBlackListKey(uid);
        redisService.saddString(key, targetId + "");
        this.applyOption(uid, targetId, ApplyConst.BLACK_APPLY.getCode());
        return getBlacklist(uid);
    }

    @Override
    public void removeBlack(long uid, long targetUid) {
        String key = RedisKeyUtils.getBlackListKey(uid);
        redisService.sremString(key, targetUid + "");
        String applyKey = RedisKeyUtils.getFriendApplyListKey(uid);
        redisService.hdel(applyKey, targetUid + "");
    }

    @Override
    public boolean isban(long uid, long targetId) {
        List<Long> bans = this.getBlacklist(uid);
        return bans.stream().anyMatch(banId -> banId.equals(targetId));
    }
}
