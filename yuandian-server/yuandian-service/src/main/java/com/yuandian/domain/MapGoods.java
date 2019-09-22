package com.yuandian.domain;

/**
 * @author: luyufeng
 * @Date: 2019/7/21
 * 地图上的物品信息
 */

public class MapGoods {
    /**
     * 经纬度
     */
    private double longitude;
    private double latitude;

    private long gid;
    private long itemId;
    private long masterUid;

    public long getMasterUid() {
        return masterUid;
    }

    public void setMasterUid(long masterUid) {
        this.masterUid = masterUid;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

}
