package com.yuandian.mapper;

import com.yuandian.entity.DiaryPO;
import com.yuandian.entity.DiaryPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Component
public interface DiaryPOMapper {
    @SelectProvider(type=DiaryPOSqlProvider.class, method="countByExample")
    int countByExample(DiaryPOExample example);

    @DeleteProvider(type=DiaryPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(DiaryPOExample example);

    @Delete({
        "delete from diary",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into diary (id, goods_id, ",
        "goods_type, uid, pic_url, ",
        "video_url, content)",
        "values (#{id,jdbcType=BIGINT}, #{goodsId,jdbcType=BIGINT}, ",
        "#{goodsType,jdbcType=INTEGER}, #{uid,jdbcType=BIGINT}, #{picUrl,jdbcType=VARCHAR}, ",
        "#{videoUrl,jdbcType=VARCHAR}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(DiaryPO record);

    @InsertProvider(type=DiaryPOSqlProvider.class, method="insertSelective")
    int insertSelective(DiaryPO record);

    @SelectProvider(type=DiaryPOSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.BIGINT),
        @Result(column="goods_type", property="goodsType", jdbcType=JdbcType.INTEGER),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="video_url", property="videoUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<DiaryPO> selectByExampleWithBLOBs(DiaryPOExample example);

    @SelectProvider(type=DiaryPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.BIGINT),
        @Result(column="goods_type", property="goodsType", jdbcType=JdbcType.INTEGER),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="video_url", property="videoUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<DiaryPO> selectByExample(DiaryPOExample example);

    @Select({
        "select",
        "id, goods_id, goods_type, uid, pic_url, video_url, content",
        "from diary",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.BIGINT),
        @Result(column="goods_type", property="goodsType", jdbcType=JdbcType.INTEGER),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="video_url", property="videoUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    DiaryPO selectByPrimaryKey(Long id);

    @UpdateProvider(type=DiaryPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DiaryPO record);

    @Update({
        "update diary",
        "set goods_id = #{goodsId,jdbcType=BIGINT},",
          "goods_type = #{goodsType,jdbcType=INTEGER},",
          "uid = #{uid,jdbcType=BIGINT},",
          "pic_url = #{picUrl,jdbcType=VARCHAR},",
          "video_url = #{videoUrl,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(DiaryPO record);

    @Update({
        "update diary",
        "set goods_id = #{goodsId,jdbcType=BIGINT},",
          "goods_type = #{goodsType,jdbcType=INTEGER},",
          "uid = #{uid,jdbcType=BIGINT},",
          "pic_url = #{picUrl,jdbcType=VARCHAR},",
          "video_url = #{videoUrl,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(DiaryPO record);
}