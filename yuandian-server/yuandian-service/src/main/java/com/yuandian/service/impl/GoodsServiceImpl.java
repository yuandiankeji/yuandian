package com.yuandian.service.impl;

import com.yuandian.entity.GoodsPo;
import com.yuandian.entity.GoodsPoExample;
import com.yuandian.mapper.GoodsPoMapper;
import com.yuandian.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/7/7
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsPoMapper goodsPoMapper;

    @Override
    public GoodsPo getGoodsById(Long id) {
        return goodsPoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsPo> getAllGoods() {
        return goodsPoMapper.selectByExample(new GoodsPoExample());
    }
}
