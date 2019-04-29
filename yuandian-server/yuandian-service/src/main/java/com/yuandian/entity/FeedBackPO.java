package com.yuandian.entity;

public class FeedBackPO {
    private Long id;

    private Long uid;

    private String feedBack;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack == null ? null : feedBack.trim();
    }
}