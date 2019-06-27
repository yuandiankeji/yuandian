package com.yuandian.server.logic.mapper;

import com.yuandian.server.logic.model.entity.UserPo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPoMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    UserPo selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);
}