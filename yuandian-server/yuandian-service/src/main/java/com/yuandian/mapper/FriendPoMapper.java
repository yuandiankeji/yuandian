package com.yuandian.mapper;

import com.yuandian.entity.FriendPo;
import com.yuandian.entity.FriendPoExample;
import com.yuandian.entity.FriendPoKey;
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
import org.springframework.stereotype.Component;

@Component
public interface FriendPoMapper {
    @SelectProvider(type=FriendPoSqlProvider.class, method="countByExample")
    int countByExample(FriendPoExample example);

    @DeleteProvider(type=FriendPoSqlProvider.class, method="deleteByExample")
    int deleteByExample(FriendPoExample example);

    @Delete({
        "delete from friend",
        "where uid = #{uid,jdbcType=INTEGER}",
          "and fuid = #{fuid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(FriendPoKey key);

    @Insert({
        "insert into friend (uid, fuid, ",
        "group_id, c_time)",
        "values (#{uid,jdbcType=INTEGER}, #{fuid,jdbcType=INTEGER}, ",
        "#{groupId,jdbcType=INTEGER}, #{cTime,jdbcType=TIMESTAMP})"
    })
    int insert(FriendPo record);

    @InsertProvider(type=FriendPoSqlProvider.class, method="insertSelective")
    int insertSelective(FriendPo record);

    @SelectProvider(type=FriendPoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fuid", property="fuid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="c_time", property="cTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FriendPo> selectByExample(FriendPoExample example);

    @Select({
        "select",
        "uid, fuid, group_id, c_time",
        "from friend",
        "where uid = #{uid,jdbcType=INTEGER}",
          "and fuid = #{fuid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fuid", property="fuid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="c_time", property="cTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FriendPo selectByPrimaryKey(FriendPoKey key);

    @UpdateProvider(type=FriendPoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FriendPo record, @Param("example") FriendPoExample example);

    @UpdateProvider(type=FriendPoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FriendPo record, @Param("example") FriendPoExample example);

    @UpdateProvider(type=FriendPoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FriendPo record);

    @Update({
        "update friend",
        "set group_id = #{groupId,jdbcType=INTEGER},",
          "c_time = #{cTime,jdbcType=TIMESTAMP}",
        "where uid = #{uid,jdbcType=INTEGER}",
          "and fuid = #{fuid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FriendPo record);
}