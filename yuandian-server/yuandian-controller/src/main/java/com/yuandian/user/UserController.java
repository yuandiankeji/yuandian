package com.yuandian.user;

import com.alibaba.fastjson.JSONObject;
import com.robert.vesta.service.intf.IdService;
import com.yuandian.core.common.Rediskey;
import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.core.utils.ValidCodeUtil;
import com.yuandian.entity.LoginPO;
import com.yuandian.entity.UserPO;
import com.yuandian.service.LoginService;
import com.yuandian.service.PhoneMsgService;
import com.yuandian.service.TokenService;
import com.yuandian.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: luyufeng
 * @Date: 2019/4/23
 */

@RestController
@RequestMapping(value = "user")
@EnableSwagger2 // 让swagger生成接口文档
public class UserController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneMsgService phoneMsgService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private IdService idService;

    @Autowired
    private TokenService tokenService;

    private static Set<String> phones = new HashSet<>();

    static {
        phones.add("18910418054");
        phones.add("13331193458");
        phones.add("18410252053");
        phones.add("17339113585");
        phones.add("1234567");
    }

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
    public ResultModel resiger(@ApiParam(value = "用户或用户各个属性") @RequestParam String phone, @RequestParam String validCode, @RequestParam String pwd, @RequestParam String deviceId) {
        ResultModel model = ResultModel.ok();
        RBucket<String> code = redissonClient.getBucket(Rediskey.MSG_CODE + phone);
        if (!code.isExists()) {
            ResultModel resultModel = new ResultModel(ResultStatus.MSG_CODE_NOT_EXIST);
            return resultModel;
        } else {
            String val = code.get();
            if (!validCode.equals(val)) {
                ResultModel resultModel = new ResultModel(ResultStatus.MSG_CODE_ERROR);
                return resultModel;
            }
            LoginPO loginPO = loginService.selectByPhone(phone);
            if (loginPO == null) {
                LoginPO loginUser = new LoginPO();
                loginUser.setPassword(pwd);
                loginUser.setPhone(phone);
                long userId = idService.genId();
                loginUser.setUid(userId);
                loginUser.setToken(tokenService.createToken(userId, deviceId).getTokenStr());
                loginService.insert(loginUser);
                UserPO userPO = new UserPO();
                userPO.setAccount("");
                userPO.setBirthday(new Date());
                userPO.sethAddress("");
                userPO.setHeadUrl("");
                userPO.setMail("");
                userPO.setQq("");
                userPO.setRegistration(new Date());
                userPO.setSex(1);
                userPO.setSignature("");
                userPO.setWechat("");
                userPO.setAge("");
                userPO.setAlipay("");
                userPO.setIp("");
                userPO.setStatus(1);
                userPO.setUid(userId);
                userPO.setPhoneNum(phone);
                userPO.setNickName(loginUser.getUid().toString());
                userService.insertUser(userPO);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userInfo", userPO);
                jsonObject.put("token", loginUser.getToken());
                model.setContent(jsonObject);
            } else {
                ResultModel resultModel = new ResultModel(ResultStatus.USER_EXIST);
                return resultModel;
            }
        }
        return model;
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel update(@ApiParam(value = "用户各个属性") @RequestBody UserPO userPO) {
        userService.updateUser(userPO);
        return ResultModel.ok();
    }

    @ApiOperation(value = "发送验证码")
    @RequestMapping(value = "/sendValidCode", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel sendValidCode(@ApiParam(value = "手机号") @RequestParam String phone) throws UnsupportedEncodingException {
        if (phones.contains(phone)) {
            RBucket<String> code = redissonClient.getBucket(Rediskey.MSG_CODE + phone);
            if (code.isExists()) {
                ResultModel resultModel = new ResultModel(ResultStatus.MSG_CODE_EXIST);
                return resultModel;
            }
            String random = ValidCodeUtil.getNonce_str();
            code.set(random, 59, TimeUnit.SECONDS);
            phoneMsgService.sendPhoneMsg(phone, random);
        }
        return ResultModel.ok();
    }


}
