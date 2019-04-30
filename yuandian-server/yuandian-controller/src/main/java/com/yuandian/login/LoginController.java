package com.yuandian.login;

import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.entity.LoginPO;
import com.yuandian.entity.Token;
import com.yuandian.service.LoginService;
import com.yuandian.service.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: luyufeng
 * @Date: 2019/3/25
 */

@RestController
@RequestMapping(value = "/auth")
@EnableSwagger2
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public ResultModel login(@ApiParam("用户id") @RequestParam long uid, @ApiParam("密码") @RequestParam String password, @ApiParam("设备id") @RequestParam String deviceId) {
        LoginPO loginPO = loginService.selectByUid(uid);
        ResultModel resultModel;
        if (loginPO != null && loginPO.getPassword().equals(password)) {
             resultModel = new ResultModel(ResultStatus.SUCCESS);
            Token token = tokenService.createToken(uid, deviceId);
            resultModel.setContent(token);
        } else {
            resultModel = ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
        }
        return resultModel;
    }

    @PostMapping("/logout/{uid}")
    @ApiOperation("注销")
    public ResultModel logout(@ApiParam("用户id") @PathVariable long uid) {
        tokenService.deleteToken(uid);
        ResultModel resultModel = new ResultModel(ResultStatus.SUCCESS);
        return resultModel;
    }

    @ApiOperation("修改密码")
    @PostMapping("/password")
    public ResultModel updatePassword(@ApiParam("用户id") @RequestParam long uid,@ApiParam("密码") @RequestParam String password) {
        LoginPO loginPO = loginService.selectByUid(uid);
        if (loginPO != null) {
            loginPO.setPassword(password);
            loginService.update(loginPO);
            return ResultModel.ok();
        }
        return ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
    }
}
