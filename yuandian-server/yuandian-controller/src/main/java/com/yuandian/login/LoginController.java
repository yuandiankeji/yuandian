package com.yuandian.login;

import com.alibaba.fastjson.JSONObject;
import com.yuandian.core.common.Rediskey;
import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.entity.LoginPO;
import com.yuandian.entity.Token;
import com.yuandian.entity.UserPO;
import com.yuandian.service.LoginService;
import com.yuandian.service.TokenService;
import com.yuandian.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
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

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private UserService userService;

    @ApiOperation("登录接口")
    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel login(@ApiParam("手机号") @RequestParam String phone, @ApiParam("密码或验证码") @RequestParam String pwd, @ApiParam("设备id") @RequestParam String deviceId, @ApiParam("登录方式0验证码1密码") @RequestParam int type) {
        LoginPO loginPO = loginService.selectByPhone(phone);
        ResultModel resultModel;
        if (loginPO == null) {
            resultModel = new ResultModel(ResultStatus.USER_NOT_REGSTION);
            return resultModel;
        }
        if (loginPO != null && 0 == type) {
            RBucket<String> validCodeBuck = redissonClient.getBucket(Rediskey.MSG_CODE + phone);

            if (!validCodeBuck.isExists() || !validCodeBuck.get().equals(pwd)) {
                resultModel = new ResultModel(ResultStatus.MSG_CODE_ERROR);
                return resultModel;
            }

            resultModel = ResultModel.ok();
            UserPO userPO = userService.selectUserById(loginPO.getUid());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userInfo", userPO);
            jsonObject.put("token", tokenService.createToken(loginPO.getUid(), deviceId).getTokenStr());
            resultModel.setContent(jsonObject);

        } else {
            if (loginPO != null && loginPO.getPassword().equals(pwd)) {
                UserPO userPO = userService.selectUserById(loginPO.getUid());
                resultModel = new ResultModel(ResultStatus.SUCCESS);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userInfo", userPO);
                jsonObject.put("token", tokenService.createToken(loginPO.getUid(), deviceId).getTokenStr());
                resultModel.setContent(jsonObject);
            } else {
                resultModel = ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
            }
        }

        return resultModel;
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation("注销")
    public ResultModel logout(@ApiParam("uid") @RequestParam long uid) {
        tokenService.deleteToken(uid);
        ResultModel resultModel = new ResultModel(ResultStatus.SUCCESS);
        return resultModel;
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/password", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel updatePassword(@ApiParam("用户uid") @RequestParam long uid, @ApiParam("老密码") @RequestParam String oldPwd, @ApiParam("新密码") @RequestParam String newPwd) {
        LoginPO loginPO = loginService.selectByUid(uid);

        if (loginPO != null) {
            if (loginPO.getPassword().equals(oldPwd)) {
                loginPO.setPassword(newPwd);
                loginService.update(loginPO);
                return ResultModel.ok();
            } else {
                ResultModel resultModel = new ResultModel(ResultStatus.OLD_PWD_ERROR);
                return resultModel;
            }

        } else {
            ResultModel resultModel = new ResultModel(ResultStatus.USER_NOT_FOUND);
            return resultModel;
        }
    }

    @ApiOperation("更换手机号")
    @RequestMapping(value = "/changePhone", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel changePhone(@ApiParam("用户uid") @RequestParam long uid, @ApiParam("新手机号") @RequestParam String newPhone, @RequestParam String validCode) {
        LoginPO phonePo = loginService.selectByPhone(newPhone);
        ResultModel resultModel;

        if (phonePo != null) {
            resultModel = new ResultModel(ResultStatus.USER_EXIST);
            return resultModel;
        }

        LoginPO loginPO = loginService.selectByUid(uid);
        if (loginPO != null) {
            RBucket<String> validCodeBuck = redissonClient.getBucket(Rediskey.MSG_CODE + newPhone);
            if (!validCodeBuck.isExists() || !validCodeBuck.get().equals(validCode)) {
                resultModel = new ResultModel(ResultStatus.MSG_CODE_ERROR);
                return resultModel;
            }
            UserPO userPO = userService.selectUserById(uid);
            userPO.setPhoneNum(newPhone);
            userService.updateUser(userPO);
            loginPO.setPhone(newPhone);
            loginService.update(loginPO);

        } else {
            resultModel = new ResultModel(ResultStatus.USER_NOT_FOUND);
            return resultModel;
        }
        return ResultModel.ok();
    }

    @ApiOperation("忘记密码")
    @RequestMapping(value = "/forgetPassword", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel forgetPassword(@ApiParam("手机号") @RequestParam String phone, @ApiParam("新密码") @RequestParam String newPwd, @RequestParam String validCode) {
        LoginPO loginPO = loginService.selectByPhone(phone);
        ResultModel resultModel;

        if (loginPO == null) {
            resultModel = new ResultModel(ResultStatus.MSG_CODE_NOT_EXIST);
            return resultModel;
        }
        RBucket<String> validCodeBuck = redissonClient.getBucket(Rediskey.MSG_CODE + phone);
        if (!validCodeBuck.isExists() || !validCodeBuck.get().equals(validCode)) {
            resultModel = new ResultModel(ResultStatus.MSG_CODE_ERROR);
            return resultModel;
        }

        loginPO.setPassword(newPwd);
        loginService.update(loginPO);
        return ResultModel.ok();
    }
}
