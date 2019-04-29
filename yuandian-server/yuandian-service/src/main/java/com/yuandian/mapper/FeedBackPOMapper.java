package com.yuandian.mapper;

import com.yuandian.entity.FeedBackPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface FeedBackPOMapper {
    @Insert({
        "insert into feed_back (id, uid, feed_back)",
        "values (#{id,jdbcType=BIGINT}, #{uid,jdbcType=BIGINT}, #{feedBack,jdbcType=LONGVARCHAR})"
    })
    int insert(FeedBackPO record);

    @InsertProvider(type=FeedBackPOSqlProvider.class, method="insertSelective")
    int insertSelective(FeedBackPO record);
}