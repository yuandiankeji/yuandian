package com.yuandian.test;

import com.robert.vesta.service.intf.IdService;
import com.yuandian.service.RedisService;
import org.redisson.api.RList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
