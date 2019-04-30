package com.yuandian.login;

import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.entity.LoginPO;
import com.yuandian.entity.Token;
import com.yuandian.service.LoginService;
import com.yuandian.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: luyufeng
 * @Date: 2019/3/25
 */

@RestController
@RequestMapping(value = "/auth")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param uid
     * @param password
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResultModel login(@RequestParam long uid, @RequestParam String password, @RequestParam String deviceId) {
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

    /**
     * 注销
     * @param uid
     * @return
     */
    @RequestMapping("/logout/{uid}")
    public ResultModel logout(@PathVariable long uid) {
        tokenService.deleteToken(uid);
        ResultModel resultModel = new ResultModel(ResultStatus.SUCCESS);
        return resultModel;
    }

    /**
     * 改密码
     * @param uid
     * @param password
     * @return
     */
    @RequestMapping("/password")
    public ResultModel updatePassword(@RequestParam long uid, @RequestParam String password) {
        LoginPO loginPO = loginService.selectByUid(uid);
        if (loginPO != null) {
            loginPO.setPassword(password);
            loginService.update(loginPO);
            return ResultModel.ok();
        }
        return ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
    }
}
