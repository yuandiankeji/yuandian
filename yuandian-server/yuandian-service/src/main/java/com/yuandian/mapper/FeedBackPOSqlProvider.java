package com.yuandian.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

import com.yuandian.entity.FeedBackPO;

public class FeedBackPOSqlProvider {

    public String insertSelective(FeedBackPO record) {
        BEGIN();
        INSERT_INTO("feed_back");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getUid() != null) {
            VALUES("uid", "#{uid,jdbcType=BIGINT}");
        }
        
        if (record.getFeedBack() != null) {
            VALUES("feed_back", "#{feedBack,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }
}