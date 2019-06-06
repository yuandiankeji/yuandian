package com.yuandian.service;

import com.yuandian.entity.DiaryPO;

import java.util.List;

public interface DiaryService {
    int insert(DiaryPO record);

    List<DiaryPO> getDiarysByGoodsId(long goodsid);
}
