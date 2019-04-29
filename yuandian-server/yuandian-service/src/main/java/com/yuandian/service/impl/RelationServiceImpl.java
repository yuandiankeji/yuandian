package com.yuandian.service.impl;

import com.yuandian.core.common.Constants;
import com.yuandian.domain.UserPO;
import com.yuandian.entity.RelationPO;
import com.yuandian.mapper.RelationPOMapper;
import com.yuandian.service.RelationService;
import com.yuandian.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 * 好友关系
 */

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationPOMapper relationPOMapper;

    @Autowired
    private UserService userService;

    @Override
    public void insertRelation(RelationPO relationPO) {
        relationPOMapper.insert(relationPO);
    }

    @Override
    public List<UserPO> selectFriends(long uid) {
        RelationPO relationPO = relationPOMapper.selectByPrimaryKey(uid);
        String fuid = relationPO.getFuid();
        List<UserPO> results = new ArrayList<>();
        List<String> fuids = Arrays.asList(StringUtils.split(fuid, Constants.comma));
        for (String id : fuids) {
            UserPO userPO = userService.selectUserById(Long.valueOf(id));
            results.add(userPO);
        }
        return results;
    }

    @Override
    public void updateRelation(RelationPO relationPO) {
        relationPOMapper.updateByPrimaryKeySelective(relationPO);
    }
}
