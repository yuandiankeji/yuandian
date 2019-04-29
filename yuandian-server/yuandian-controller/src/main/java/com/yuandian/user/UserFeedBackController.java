package com.yuandian.user;

import com.yuandian.core.common.ResultModel;
import com.yuandian.entity.FeedBackPO;
import com.yuandian.service.UserFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 * 用户反馈
 */

@RestController
@RequestMapping(value = "userFeedBack")
public class UserFeedBackController {

    @Autowired
    private UserFeedBackService userFeedBackService;

    @PostMapping("/feedBack")
    public ResultModel feedBack(@RequestBody FeedBackPO feedBackPO) {
        userFeedBackService.insertFeedBack(feedBackPO);
        return ResultModel.ok();
    }
}
