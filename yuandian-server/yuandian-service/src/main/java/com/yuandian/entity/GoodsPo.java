package com.yuandian.entity;

public class GoodsPo {
    private Long id;

    private String name;

    private String slogan;

    private Byte stackable;

    private String thumPic;

    private String iosABRes;

    private String androidABRes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan == null ? null : slogan.trim();
    }

    public Byte getStackable() {
        return stackable;
    }

    public void setStackable(Byte stackable) {
        this.stackable = stackable;
    }

    public String getThumPic() {
        return thumPic;
    }

    public void setThumPic(String thumPic) {
        this.thumPic = thumPic == null ? null : thumPic.trim();
    }

    public String getIosABRes() {
        return iosABRes;
    }

    public void setIosABRes(String iosABRes) {
        this.iosABRes = iosABRes;
    }

    public String getAndroidABRes() {
        return androidABRes;
    }

    public void setAndroidABRes(String androidABRes) {
        this.androidABRes = androidABRes;
    }
}