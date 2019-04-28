package com.yuandian.mapper;

import com.yuandian.domain.LoginPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Component
public interface LoginPOMapper {
    @Delete({
        "delete from login",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long uid);

    @Insert({
        "insert into login (uid, token)",
        "values (#{uid,jdbcType=BIGINT}, #{token,jdbcType=VARCHAR})"
    })
    int insert(LoginPO record);

    @InsertProvider(type=LoginPOSqlProvider.class, method="insertSelective")
    int insertSelective(LoginPO record);

    @Select({
        "select",
        "uid, token",
        "from login",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR)
    })
    LoginPO selectByPrimaryKey(Long uid);

    @UpdateProvider(type=LoginPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(LoginPO record);

    @Update({
        "update login",
        "set token = #{token,jdbcType=VARCHAR}",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(LoginPO record);
}