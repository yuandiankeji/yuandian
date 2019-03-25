package com.yuandian.test;

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
    @GetMapping(value = "/success")
    public Object success() {
        return "success";
    }
}
