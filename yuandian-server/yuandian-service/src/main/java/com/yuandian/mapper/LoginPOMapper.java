package com.yuandian.mapper;

import com.yuandian.entity.LoginPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
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
        "insert into login (uid, token, ",
        "password)",
        "values (#{uid,jdbcType=BIGINT}, #{token,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR})"
    })
    int insert(LoginPO record);

    @InsertProvider(type=LoginPOSqlProvider.class, method="insertSelective")
    int insertSelective(LoginPO record);

    @Select({
        "select",
        "uid, token, password",
        "from login",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    LoginPO selectByPrimaryKey(Long uid);

    @UpdateProvider(type=LoginPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(LoginPO record);

    @Update({
        "update login",
        "set token = #{token,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR}",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(LoginPO record);
}