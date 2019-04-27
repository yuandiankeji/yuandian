package com.yuandian.mapper;

import com.yuandian.domain.LoginPO;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class LoginPOSqlProvider {

    public String insertSelective(LoginPO record) {
        BEGIN();
        INSERT_INTO("login");
        
        if (record.getUid() != null) {
            VALUES("uid", "#{uid,jdbcType=BIGINT}");
        }
        
        if (record.getToken() != null) {
            VALUES("token", "#{token,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(LoginPO record) {
        BEGIN();
        UPDATE("login");
        
        if (record.getToken() != null) {
            SET("token = #{token,jdbcType=VARCHAR}");
        }
        
        WHERE("uid = #{uid,jdbcType=BIGINT}");
        
        return SQL();
    }
}