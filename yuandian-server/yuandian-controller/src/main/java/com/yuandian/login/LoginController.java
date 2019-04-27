package com.yuandian.login;

import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.domain.UserPO;
import com.yuandian.service.TokenService;
import com.yuandian.service.UserService;
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
    private UserService userService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResultModel login(@RequestParam long uid, @RequestParam String password) {
        UserPO userPO = userService.selectUserById(uid);
        ResultModel resultModel = null;
        if (userPO != null && userPO.getPassWord().equals(password)) {
             resultModel = new ResultModel(ResultStatus.SUCCESS);
             resultModel.setContent(resultModel);
        } else {
            resultModel = ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
        }

        return resultModel;
    }

    @RequestMapping("/logout/{uid}")
    public ResultModel logout(@PathVariable long uid) {
        ResultModel resultModel = new ResultModel(ResultStatus.SUCCESS);
        return resultModel;
    }
}
