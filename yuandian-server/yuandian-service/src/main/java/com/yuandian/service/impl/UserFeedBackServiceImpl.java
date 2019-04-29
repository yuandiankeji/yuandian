package com.yuandian.service.impl;

import com.yuandian.entity.FeedBackPO;
import com.yuandian.mapper.FeedBackPOMapper;
import com.yuandian.service.UserFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 * 用户反馈
 */

@Service
public class UserFeedBackServiceImpl implements UserFeedBackService {

    @Autowired
    private FeedBackPOMapper feedBackPOMapper;

    @Override
    public void insertFeedBack(FeedBackPO feedBackPO) {
        feedBackPOMapper.insert(feedBackPO);
    }
}
