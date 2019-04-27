package com.yuandian.mapper;

import com.yuandian.domain.UserPO;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class UserPOSqlProvider {

    public String insertSelective(UserPO record) {
        BEGIN();
        INSERT_INTO("user");
        
        if (record.getUid() != null) {
            VALUES("uid", "#{uid,jdbcType=BIGINT}");
        }
        
        if (record.getAccount() != null) {
            VALUES("account", "#{account,jdbcType=VARCHAR}");
        }
        
        if (record.getPhoneNum() != null) {
            VALUES("phone_num", "#{phoneNum,jdbcType=VARCHAR}");
        }
        
        if (record.getMail() != null) {
            VALUES("mail", "#{mail,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            VALUES("sex", "#{sex,jdbcType=INTEGER}");
        }
        
        if (record.getHeadUrl() != null) {
            VALUES("head_url", "#{headUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getPassWord() != null) {
            VALUES("pass_word", "#{passWord,jdbcType=VARCHAR}");
        }
        
        if (record.getNickName() != null) {
            VALUES("nick_name", "#{nickName,jdbcType=VARCHAR}");
        }
        
        if (record.getSignature() != null) {
            VALUES("signature", "#{signature,jdbcType=VARCHAR}");
        }
        
        if (record.getQq() != null) {
            VALUES("qq", "#{qq,jdbcType=VARCHAR}");
        }
        
        if (record.getWechat() != null) {
            VALUES("wechat", "#{wechat,jdbcType=VARCHAR}");
        }
        
        if (record.getAlipay() != null) {
            VALUES("alipay", "#{alipay,jdbcType=VARCHAR}");
        }
        
        if (record.getIp() != null) {
            VALUES("ip", "#{ip,jdbcType=VARCHAR}");
        }
        
        if (record.getRegistration() != null) {
            VALUES("registration", "#{registration,jdbcType=TIMESTAMP}");
        }
        
        if (record.gethAddress() != null) {
            VALUES("h_address", "#{hAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            VALUES("birthday", "#{birthday,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(UserPO record) {
        BEGIN();
        UPDATE("user");
        
        if (record.getAccount() != null) {
            SET("account = #{account,jdbcType=VARCHAR}");
        }
        
        if (record.getPhoneNum() != null) {
            SET("phone_num = #{phoneNum,jdbcType=VARCHAR}");
        }
        
        if (record.getMail() != null) {
            SET("mail = #{mail,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            SET("sex = #{sex,jdbcType=INTEGER}");
        }
        
        if (record.getHeadUrl() != null) {
            SET("head_url = #{headUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getPassWord() != null) {
            SET("pass_word = #{passWord,jdbcType=VARCHAR}");
        }
        
        if (record.getNickName() != null) {
            SET("nick_name = #{nickName,jdbcType=VARCHAR}");
        }
        
        if (record.getSignature() != null) {
            SET("signature = #{signature,jdbcType=VARCHAR}");
        }
        
        if (record.getQq() != null) {
            SET("qq = #{qq,jdbcType=VARCHAR}");
        }
        
        if (record.getWechat() != null) {
            SET("wechat = #{wechat,jdbcType=VARCHAR}");
        }
        
        if (record.getAlipay() != null) {
            SET("alipay = #{alipay,jdbcType=VARCHAR}");
        }
        
        if (record.getIp() != null) {
            SET("ip = #{ip,jdbcType=VARCHAR}");
        }
        
        if (record.getRegistration() != null) {
            SET("registration = #{registration,jdbcType=TIMESTAMP}");
        }
        
        if (record.gethAddress() != null) {
            SET("h_address = #{hAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            SET("birthday = #{birthday,jdbcType=TIMESTAMP}");
        }
        
        WHERE("uid = #{uid,jdbcType=BIGINT}");
        
        return SQL();
    }
}