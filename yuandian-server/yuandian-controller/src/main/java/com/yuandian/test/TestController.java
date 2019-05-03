package com.yuandian.test;

import com.robert.vesta.service.intf.IdService;
import com.yuandian.core.annotation.Authorization;
import com.yuandian.core.common.ResultModel;
import com.yuandian.entity.LoginPO;
import com.yuandian.entity.UserPO;
import com.yuandian.service.LoginService;
import com.yuandian.core.utils.RedisService;
import com.yuandian.service.TokenService;
import com.yuandian.service.UserService;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

/**
 * @author: luyufeng
 * @Date: 2019/3/25
 */

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private IdService idService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/success")
    public Object success() {
        RList<String> list = redisService.getRList("testList");
        list.add("a");
        list.add("bb");
        list.add("ccc");
        return "success";
    }

    @PostMapping("/getId")
    public long getId() {
        return idService.genId();
    }

    @RequestMapping(value = "/clientTest", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel clientTest(@RequestParam String id) {
        HashMap<String, String> res = new HashMap<>();
        res.put("id", id);
        return ResultModel.ok(res);

    }

    @Authorization
    @GetMapping("/auth")
    public String testAuth() {
        return "";
    }

    @GetMapping("/insert")
    public String insertLogin() {
        LoginPO po = new LoginPO();
        po.setToken("ewte34tdfg");
        po.setUid(idService.genId());
        loginService.insert(po);
        RMap<String,Object> rMap = redisService.getRMap("test");
        return "suc";
    }

    @GetMapping("/getCache/{uid}")
    public Object getCahche(@PathVariable long uid) {
        return loginService.selectByUid(uid);
    }

    @GetMapping("/insertUser")
    public String insertUser() {
        UserPO userPO = getOneMockUser();
        userService.insertUser(userPO);
        return "success";
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
