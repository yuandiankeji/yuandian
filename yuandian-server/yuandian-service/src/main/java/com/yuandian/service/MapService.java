package com.yuandian.service;

import com.yuandian.domain.MapGoods;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/7/21
 */

public interface MapService {
    void putGoods(double longitude, double latitude, long itemId, long gid);
    List<MapGoods> getNearByGoods(double longitude, double latitude, int range);
    boolean hasGoods(double longitude, double latitude, long itemId, long gid);
    void takeFromMap(double longitude, double latitude,long itemId, long gid);
}
