package com.yuandian.mapper;

import com.yuandian.domain.UserPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Component
public interface UserPOMapper {
    @Delete({
        "delete from user",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long uid);

    @Insert({
        "insert into user (uid, account, ",
        "phone_num, mail, ",
        "sex, head_url, pass_word, ",
        "nick_name, signature, ",
        "qq, wechat, alipay, ",
        "ip, registration, ",
        "h_address, birthday)",
        "values (#{uid,jdbcType=BIGINT}, #{account,jdbcType=VARCHAR}, ",
        "#{phoneNum,jdbcType=VARCHAR}, #{mail,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=INTEGER}, #{headUrl,jdbcType=VARCHAR}, #{passWord,jdbcType=VARCHAR}, ",
        "#{nickName,jdbcType=VARCHAR}, #{signature,jdbcType=VARCHAR}, ",
        "#{qq,jdbcType=VARCHAR}, #{wechat,jdbcType=VARCHAR}, #{alipay,jdbcType=VARCHAR}, ",
        "#{ip,jdbcType=VARCHAR}, #{registration,jdbcType=TIMESTAMP}, ",
        "#{hAddress,jdbcType=VARCHAR}, #{birthday,jdbcType=TIMESTAMP})"
    })
    int insert(UserPO record);

    @InsertProvider(type=UserPOSqlProvider.class, method="insertSelective")
    int insertSelective(UserPO record);

    @Select({
        "select",
        "uid, account, phone_num, mail, sex, head_url, pass_word, nick_name, signature, ",
        "qq, wechat, alipay, ip, registration, h_address, birthday",
        "from user",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_num", property="phoneNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.INTEGER),
        @Result(column="head_url", property="headUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="pass_word", property="passWord", jdbcType=JdbcType.VARCHAR),
        @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat", property="wechat", jdbcType=JdbcType.VARCHAR),
        @Result(column="alipay", property="alipay", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="registration", property="registration", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="h_address", property="hAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.TIMESTAMP)
    })
    UserPO selectByPrimaryKey(Long uid);

    @UpdateProvider(type=UserPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserPO record);

    @Update({
        "update user",
        "set account = #{account,jdbcType=VARCHAR},",
          "phone_num = #{phoneNum,jdbcType=VARCHAR},",
          "mail = #{mail,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=INTEGER},",
          "head_url = #{headUrl,jdbcType=VARCHAR},",
          "pass_word = #{passWord,jdbcType=VARCHAR},",
          "nick_name = #{nickName,jdbcType=VARCHAR},",
          "signature = #{signature,jdbcType=VARCHAR},",
          "qq = #{qq,jdbcType=VARCHAR},",
          "wechat = #{wechat,jdbcType=VARCHAR},",
          "alipay = #{alipay,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "registration = #{registration,jdbcType=TIMESTAMP},",
          "h_address = #{hAddress,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=TIMESTAMP}",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserPO record);
}