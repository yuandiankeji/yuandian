package com.yuandian.user;

import com.robert.vesta.service.intf.IdService;
import com.yuandian.core.common.ResultModel;
import com.yuandian.entity.UserPO;
import com.yuandian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author: luyufeng
 * @Date: 2019/4/23
 */

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IdService idService;

    @GetMapping("/info/{uid}")
    public ResultModel getUserInfo(@PathVariable long uid) {
        UserPO userPO = userService.selectUserById(uid);
        ResultModel result = ResultModel.ok();
        result.setContent(userPO);
        return result;
    }

    @GetMapping("/resiger")
    public ResultModel resiger(@RequestBody UserPO userPO) {
        userService.insertUser(userPO);
        return ResultModel.ok();
    }

    @PostMapping("/update")
    public ResultModel update(@RequestBody UserPO userPO) {
        userService.updateUser(userPO);
        return ResultModel.ok();
    }


    public UserPO getOneMockUser() {
        UserPO userPO = new UserPO();
        userPO.setUid(idService.genId());
        userPO.setNickName("rick");
        userPO.setAccount("178323wefw");
        userPO.setBirthday(new Date());
        userPO.setRegistration(new Date());
        userPO.setHeadUrl("/user/1234566.jpg");
        userPO.setMail("179979304@qq.com");
        userPO.setPhoneNum("18910430596");
        userPO.setQq("179979304");
        userPO.setSex(1);
        userPO.setSignature("在这里大家真诚交流");
        userPO.setWechat("luyufeng_24");
        userPO.sethAddress("我的地址");
        return userPO;
    }
}
