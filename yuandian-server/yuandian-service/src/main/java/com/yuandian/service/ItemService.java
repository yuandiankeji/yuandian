package com.yuandian.service;

import com.yuandian.entity.ItemPo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/7/21
 */

public interface ItemService {
    void insertToBag(ItemPo itemPo);
    List<ItemPo> getAllItems(long uid);
    ItemPo getItemPoById(long itemId);
}
