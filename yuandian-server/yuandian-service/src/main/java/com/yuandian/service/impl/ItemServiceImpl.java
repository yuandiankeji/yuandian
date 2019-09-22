package com.yuandian.service.impl;

import com.yuandian.entity.GoodsPo;
import com.yuandian.entity.ItemPo;
import com.yuandian.entity.ItemPoExample;
import com.yuandian.mapper.ItemPoMapper;
import com.yuandian.service.GoodsService;
import com.yuandian.service.ItemService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/7/21
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemPoMapper itemPoMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    public void insertToBag(ItemPo itemPo) {
        long gid = itemPo.getGid();
        GoodsPo goodsPo = goodsService.getGoodsById(gid);
        ItemPoExample itemPoExample = new ItemPoExample();
        ItemPoExample.Criteria criteria = itemPoExample.createCriteria();
        criteria.andUidEqualTo(itemPo.getUid());
        criteria.andGidEqualTo(itemPo.getGid());
        List<ItemPo> itemPos = itemPoMapper.selectByExample(itemPoExample);
        if (CollectionUtils.isEmpty(itemPos)) {
            itemPoMapper.insert(itemPo);
            return;
        }
        if (goodsPo.getStackable() == 1) {
            itemPoMapper.updateByExampleSelective(itemPo, itemPoExample);
        } else {
            itemPoMapper.insert(itemPo);
        }
    }

    @Override
    public List<ItemPo> getAllItems(long uid) {
        ItemPoExample example = new ItemPoExample();
        ItemPoExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        return itemPoMapper.selectByExample(example);
    }

    @Override
    public ItemPo getItemPoById(long itemId) {
        return itemPoMapper.selectByPrimaryKey(itemId);
    }
}
