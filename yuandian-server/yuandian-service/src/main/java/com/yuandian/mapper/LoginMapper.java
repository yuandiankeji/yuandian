package com.yuandian.mapper;

import com.yuandian.core.entity.login.LoginPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author: luyufeng
 * @Date: 2019/4/5
 */

@Mapper
@Component
public interface LoginMapper {

    @Insert("INSERT INTO login(uid,token) VALUES(#{uid}, #{token})")
    void insert(LoginPO loginPO);

    @Select("SELECT * FROM login WHERE uid = #{uid}")
    LoginPO selectByUid(long uid);
}
