package com.yuandian.mapper;

import com.yuandian.core.entity.user.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

/**
 * @author: luyufeng
 * @Date: 2019/4/23
 */

@Component
public interface UserMapper {

    @Insert("INSERT INTO user(uid,token) VALUES(#{uid}, #{token})")
    void insert(UserPO userPO);
}
