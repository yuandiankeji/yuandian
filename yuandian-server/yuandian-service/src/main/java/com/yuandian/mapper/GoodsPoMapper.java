package com.yuandian.mapper;

import com.yuandian.entity.GoodsPo;
import com.yuandian.entity.GoodsPoExample;
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

public interface GoodsPoMapper {
    @SelectProvider(type=GoodsPoSqlProvider.class, method="countByExample")
    int countByExample(GoodsPoExample example);

    @DeleteProvider(type=GoodsPoSqlProvider.class, method="deleteByExample")
    int deleteByExample(GoodsPoExample example);

    @Delete({
        "delete from goods",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into goods (id, name, ",
        "slogan, stackable, ",
        "thum_pic, ios_res, ",
        "android_res)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{slogan,jdbcType=VARCHAR}, #{stackable,jdbcType=TINYINT}, ",
        "#{thumPic,jdbcType=VARCHAR}, #{iosRes,jdbcType=VARCHAR}, ",
        "#{androidRes,jdbcType=VARCHAR})"
    })
    int insert(GoodsPo record);

    @InsertProvider(type=GoodsPoSqlProvider.class, method="insertSelective")
    int insertSelective(GoodsPo record);

    @SelectProvider(type=GoodsPoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="slogan", property="slogan", jdbcType=JdbcType.VARCHAR),
        @Result(column="stackable", property="stackable", jdbcType=JdbcType.TINYINT),
        @Result(column="thum_pic", property="thumPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="ios_res", property="iosRes", jdbcType=JdbcType.VARCHAR),
        @Result(column="android_res", property="androidRes", jdbcType=JdbcType.VARCHAR)
    })
    List<GoodsPo> selectByExample(GoodsPoExample example);

    @Select({
        "select",
        "id, name, slogan, stackable, thum_pic, ios_res, android_res",
        "from goods",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="slogan", property="slogan", jdbcType=JdbcType.VARCHAR),
        @Result(column="stackable", property="stackable", jdbcType=JdbcType.TINYINT),
        @Result(column="thum_pic", property="thumPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="ios_res", property="iosRes", jdbcType=JdbcType.VARCHAR),
        @Result(column="android_res", property="androidRes", jdbcType=JdbcType.VARCHAR)
    })
    GoodsPo selectByPrimaryKey(Long id);

    @UpdateProvider(type=GoodsPoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GoodsPo record, @Param("example") GoodsPoExample example);

    @UpdateProvider(type=GoodsPoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") GoodsPo record, @Param("example") GoodsPoExample example);

    @UpdateProvider(type=GoodsPoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GoodsPo record);

    @Update({
        "update goods",
        "set name = #{name,jdbcType=VARCHAR},",
          "slogan = #{slogan,jdbcType=VARCHAR},",
          "stackable = #{stackable,jdbcType=TINYINT},",
          "thum_pic = #{thumPic,jdbcType=VARCHAR},",
          "ios_res = #{iosRes,jdbcType=VARCHAR},",
          "android_res = #{androidRes,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(GoodsPo record);
}