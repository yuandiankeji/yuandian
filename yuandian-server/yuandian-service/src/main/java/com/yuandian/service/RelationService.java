package com.yuandian.service;

import com.yuandian.domain.UserPO;
import com.yuandian.entity.RelationPO;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 */

public interface RelationService {
    void insertRelation(RelationPO relationPO);
    List<UserPO> selectFriends(long uid);
    void updateRelation(RelationPO relationPO);
}
