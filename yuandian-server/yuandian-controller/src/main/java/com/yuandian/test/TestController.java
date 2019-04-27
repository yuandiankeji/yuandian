package com.yuandian.test;

import com.robert.vesta.service.intf.IdService;
import com.yuandian.core.annotation.Authorization;
import com.yuandian.domain.LoginPO;
import com.yuandian.service.LoginService;
import com.yuandian.core.utils.RedisService;
import com.yuandian.service.TokenService;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getId")
    public long getId() {
        return idService.genId();
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


}
