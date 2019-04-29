package com.yuandian.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.yuandian.entity.ReportPO;

public class ReportPOSqlProvider {

    public String insertSelective(ReportPO record) {
        BEGIN();
        INSERT_INTO("report");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getReportUid() != null) {
            VALUES("report_uid", "#{reportUid,jdbcType=BIGINT}");
        }
        
        if (record.getReportedUid() != null) {
            VALUES("reported_uid", "#{reportedUid,jdbcType=BIGINT}");
        }
        
        if (record.getReason() != null) {
            VALUES("reason", "#{reason,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(ReportPO record) {
        BEGIN();
        UPDATE("report");
        
        if (record.getReportUid() != null) {
            SET("report_uid = #{reportUid,jdbcType=BIGINT}");
        }
        
        if (record.getReportedUid() != null) {
            SET("reported_uid = #{reportedUid,jdbcType=BIGINT}");
        }
        
        if (record.getReason() != null) {
            SET("reason = #{reason,jdbcType=LONGVARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }
}