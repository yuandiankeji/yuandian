package com.yuandian.service.impl;

import com.robert.vesta.service.intf.IdService;
import com.yuandian.entity.DiaryPO;
import com.yuandian.entity.DiaryPOExample;
import com.yuandian.mapper.DiaryPOMapper;
import com.yuandian.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryPOMapper diaryPOMapper;

    @Autowired
    private IdService idService;

    public int insert(DiaryPO record) {
        long id = idService.genId();
        record.setId(id);
       return diaryPOMapper.insert(record);
    }

    @Override
    public List<DiaryPO> getDiarysByGoodsId(long goodsId) {
        DiaryPOExample example = new DiaryPOExample();

        DiaryPOExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        return diaryPOMapper.selectByExampleWithBLOBs(example);
    }


}
