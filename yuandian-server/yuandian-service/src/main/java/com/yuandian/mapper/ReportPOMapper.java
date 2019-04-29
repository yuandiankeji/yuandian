package com.yuandian.mapper;

import com.yuandian.entity.ReportPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ReportPOMapper {
    @Delete({
        "delete from report",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into report (id, report_uid, ",
        "reported_uid, reason)",
        "values (#{id,jdbcType=BIGINT}, #{reportUid,jdbcType=BIGINT}, ",
        "#{reportedUid,jdbcType=BIGINT}, #{reason,jdbcType=LONGVARCHAR})"
    })
    int insert(ReportPO record);

    @InsertProvider(type=ReportPOSqlProvider.class, method="insertSelective")
    int insertSelective(ReportPO record);

    @Select({
        "select",
        "id, report_uid, reported_uid, reason",
        "from report",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="report_uid", property="reportUid", jdbcType=JdbcType.BIGINT),
        @Result(column="reported_uid", property="reportedUid", jdbcType=JdbcType.BIGINT),
        @Result(column="reason", property="reason", jdbcType=JdbcType.LONGVARCHAR)
    })
    ReportPO selectByPrimaryKey(Long id);

    @UpdateProvider(type=ReportPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ReportPO record);

    @Update({
        "update report",
        "set report_uid = #{reportUid,jdbcType=BIGINT},",
          "reported_uid = #{reportedUid,jdbcType=BIGINT},",
          "reason = #{reason,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(ReportPO record);

    @Update({
        "update report",
        "set report_uid = #{reportUid,jdbcType=BIGINT},",
          "reported_uid = #{reportedUid,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ReportPO record);
}