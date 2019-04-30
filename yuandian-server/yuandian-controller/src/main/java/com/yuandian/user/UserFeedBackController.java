package com.yuandian.user;

import com.yuandian.core.common.ResultModel;
import com.yuandian.entity.FeedBackPO;
import com.yuandian.service.UserFeedBackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 * 用户反馈
 */

@RestController
@RequestMapping(value = "userFeedBack")
@EnableSwagger2
public class UserFeedBackController {

    @Autowired
    private UserFeedBackService userFeedBackService;

    @ApiOperation("用户反馈")
    @PostMapping("/feedBack")
    public ResultModel feedBack(@ApiParam("反馈信息") @RequestBody FeedBackPO feedBackPO) {
        userFeedBackService.insertFeedBack(feedBackPO);
        return ResultModel.ok();
    }
}
