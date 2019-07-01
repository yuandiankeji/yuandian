package com.yuandian.server.logic.mapper;

import com.yuandian.server.logic.model.entity.FriendPo;
import com.yuandian.server.logic.model.entity.FriendPoKey;

public interface FriendPoMapper {
    int deleteByPrimaryKey(FriendPoKey key);

    int insert(FriendPo record);

    int insertSelective(FriendPo record);

    FriendPo selectByPrimaryKey(FriendPoKey key);

    int updateByPrimaryKeySelective(FriendPo record);

    int updateByPrimaryKey(FriendPo record);
}