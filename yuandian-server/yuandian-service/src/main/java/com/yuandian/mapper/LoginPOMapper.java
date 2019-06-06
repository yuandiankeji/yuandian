package com.yuandian.mapper;

import com.yuandian.entity.LoginPO;
import com.yuandian.entity.LoginPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Component
public interface LoginPOMapper {
    @DeleteProvider(type=LoginPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(LoginPOExample example);

    @Delete({
        "delete from login",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long uid);

    @Insert({
        "insert into login (uid, phone, ",
        "token, password)",
        "values (#{uid,jdbcType=BIGINT}, #{phone,jdbcType=VARCHAR}, ",
        "#{token,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR})"
    })
    int insert(LoginPO record);

    @InsertProvider(type=LoginPOSqlProvider.class, method="insertSelective")
    int insertSelective(LoginPO record);

    @SelectProvider(type=LoginPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    List<LoginPO> selectByExample(LoginPOExample example);

    @Select({
        "select",
        "uid, phone, token, password",
        "from login",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    LoginPO selectByPrimaryKey(Long uid);

    @UpdateProvider(type=LoginPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") LoginPO record, @Param("example") LoginPOExample example);

    @UpdateProvider(type=LoginPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") LoginPO record, @Param("example") LoginPOExample example);

    @UpdateProvider(type=LoginPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(LoginPO record);

    @Update({
        "update login",
        "set phone = #{phone,jdbcType=VARCHAR},",
          "token = #{token,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR}",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(LoginPO record);
}