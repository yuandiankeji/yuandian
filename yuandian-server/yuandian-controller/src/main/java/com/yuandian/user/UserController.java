package com.yuandian.user;

import com.yuandian.core.common.ResultModel;
import com.yuandian.entity.UserPO;
import com.yuandian.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

/**
 * @author: luyufeng
 * @Date: 2019/4/23
 */

@RestController
@RequestMapping(value = "user")
@EnableSwagger2 // 让swagger生成接口文档
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/info", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel getUserInfo(@ApiParam("用户id") @RequestParam long uid) {
        UserPO userPO = userService.selectUserById(uid);
        ResultModel result = ResultModel.ok();
        result.setContent(userPO);
        return result;
    }

    @ApiOperation(value = "注册用户")
    @RequestMapping(value = "/resiger", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel resiger(@ApiParam(value = "用户或用户各个属性") @RequestBody UserPO userPO) {
        userPO.setBirthday(new Date());
        userService.insertUser(userPO);
        return ResultModel.ok();
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel update(@ApiParam(value = "用户各个属性") @RequestBody UserPO userPO) {
        userService.updateUser(userPO);
        return ResultModel.ok();
    }

}
