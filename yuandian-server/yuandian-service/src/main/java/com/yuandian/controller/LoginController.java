package com.yuandian.controller;

import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: luyufeng
 * @Date: 2019/3/25
 */

@RestController
@RequestMapping(value = "/auth")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResultModel login(@RequestParam String username, @RequestParam String password) {
        ResultModel resultModel = new ResultModel(ResultStatus.SUCCESS);
        return resultModel;
    }

    @RequestMapping("/logout")
    public ResultModel logout() {
        ResultModel resultModel = new ResultModel(ResultStatus.SUCCESS);
        return resultModel;
    }
}
