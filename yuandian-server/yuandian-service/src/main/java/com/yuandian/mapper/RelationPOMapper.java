package com.yuandian.mapper;

import com.yuandian.entity.RelationPO;
import com.yuandian.entity.RelationPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
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
public interface RelationPOMapper {
    @Delete({
        "delete from relation",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long uid);

    @Insert({
        "insert into relation (uid, fuid)",
        "values (#{uid,jdbcType=BIGINT}, #{fuid,jdbcType=LONGVARCHAR})"
    })
    int insert(RelationPO record);

    @InsertProvider(type=RelationPOSqlProvider.class, method="insertSelective")
    int insertSelective(RelationPO record);

    @SelectProvider(type=RelationPOSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="fuid", property="fuid", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<RelationPO> selectByExampleWithBLOBs(RelationPOExample example);

    @SelectProvider(type=RelationPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT, id=true)
    })
    List<RelationPO> selectByExample(RelationPOExample example);

    @Select({
        "select",
        "uid, fuid",
        "from relation",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="fuid", property="fuid", jdbcType=JdbcType.LONGVARCHAR)
    })
    RelationPO selectByPrimaryKey(Long uid);

    @UpdateProvider(type=RelationPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RelationPO record, @Param("example") RelationPOExample example);

    @UpdateProvider(type=RelationPOSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") RelationPO record, @Param("example") RelationPOExample example);

    @UpdateProvider(type=RelationPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RelationPO record, @Param("example") RelationPOExample example);

    @UpdateProvider(type=RelationPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RelationPO record);

    @Update({
        "update relation",
        "set fuid = #{fuid,jdbcType=LONGVARCHAR}",
        "where uid = #{uid,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(RelationPO record);
}