package com.yuandian.mapper;

import com.yuandian.entity.ItemPo;
import com.yuandian.entity.ItemPoExample;
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

public interface ItemPoMapper {
    @SelectProvider(type=ItemPoSqlProvider.class, method="countByExample")
    int countByExample(ItemPoExample example);

    @DeleteProvider(type=ItemPoSqlProvider.class, method="deleteByExample")
    int deleteByExample(ItemPoExample example);

    @Delete({
        "delete from item",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into item (id, uid, gid, ",
        "num)",
        "values (#{id,jdbcType=BIGINT}, #{uid,jdbcType=BIGINT}, #{gid,jdbcType=BIGINT}, ",
        "#{num,jdbcType=BIGINT})"
    })
    int insert(ItemPo record);

    @InsertProvider(type=ItemPoSqlProvider.class, method="insertSelective")
    int insertSelective(ItemPo record);

    @SelectProvider(type=ItemPoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="gid", property="gid", jdbcType=JdbcType.BIGINT),
        @Result(column="num", property="num", jdbcType=JdbcType.BIGINT)
    })
    List<ItemPo> selectByExample(ItemPoExample example);

    @Select({
        "select",
        "id, uid, gid, num",
        "from item",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="gid", property="gid", jdbcType=JdbcType.BIGINT),
        @Result(column="num", property="num", jdbcType=JdbcType.BIGINT)
    })
    ItemPo selectByPrimaryKey(Long id);

    @UpdateProvider(type=ItemPoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ItemPo record, @Param("example") ItemPoExample example);

    @UpdateProvider(type=ItemPoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ItemPo record, @Param("example") ItemPoExample example);

    @UpdateProvider(type=ItemPoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ItemPo record);

    @Update({
        "update item",
        "set uid = #{uid,jdbcType=BIGINT},",
          "gid = #{gid,jdbcType=BIGINT},",
          "num = #{num,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ItemPo record);
}