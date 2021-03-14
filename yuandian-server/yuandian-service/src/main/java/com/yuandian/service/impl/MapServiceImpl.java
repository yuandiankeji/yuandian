package com.yuandian.service.impl;

import com.yuandian.core.common.Constants;
import com.yuandian.core.common.Rediskey;
import com.yuandian.domain.MapGoods;
import com.yuandian.entity.ItemPo;
import com.yuandian.service.ItemService;
import com.yuandian.service.MapService;
import org.redisson.api.*;
import org.redisson.client.protocol.Encoder;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/7/21
 */
@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ItemService itemService;

    @Override
    public void putGoods(double longitude, double latitude, long itemId, long gid) {
        RGeo rGeo = redissonClient.getGeo(Rediskey.MAP_GOODS);
        rGeo.add(longitude, latitude, itemId + Constants.colon + gid);
    }

    @Override
    public List<MapGoods> getNearByGoods(double longitude, double latitude, int range) {
        RGeo<String> rGeo = redissonClient.getGeo(Rediskey.MAP_GOODS);
        List<String> list = rGeo.radius(longitude, latitude, range, GeoUnit.KILOMETERS);
        List<MapGoods> mapGoods = new ArrayList<>();
        for (String md : list) {
            String[] mdArr = md.split(Constants.colon);
            long itemId = Long.valueOf(mdArr[0]);
            long gid = Long.valueOf(mdArr[1]);
            MapGoods mapGoods1 = new MapGoods();
            mapGoods1.setGid(gid);
            mapGoods1.setItemId(itemId);
            ItemPo itemPo = itemService.getItemPoById(itemId);
        }
        return mapGoods;
    }

    @Override
    public boolean hasGoods(double longitude, double latitude, long itemId, long gid) {
        RGeo<String> rGeo = redissonClient.getGeo(Rediskey.MAP_GOODS);
        List<String> list = rGeo.radius(longitude, latitude, 0, GeoUnit.KILOMETERS);
        if (CollectionUtils.isEmpty(list)) {
            return false;
        } else {
            for (String mapGoods : list) {
                if (mapGoods.equals(itemId + Constants.colon + gid)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void takeFromMap(double longitude, double latitude, long itemId, long gid) {
        RGeo<String> rGeo = redissonClient.getGeo(Rediskey.MAP_GOODS);
        RScoredSortedSet scoredSortedSet = redissonClient.getScoredSortedSet(Rediskey.MAP_GOODS);
        scoredSortedSet.remove(itemId + Constants.colon + gid);
    }

    public static void main(String[] args) {
        JsonJacksonCodec jsonJacksonCodec = new JsonJacksonCodec();
        Encoder encoder = jsonJacksonCodec.getValueEncoder();
        MapGoods mapGoods = new MapGoods();
        mapGoods.setLatitude(123.2);
        mapGoods.setLongitude(38.5);
        mapGoods.setGid(1);
        try {
            String j = new String(encoder.encode(mapGoods));
            System.out.println(j);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
