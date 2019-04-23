package com.yuandian.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: luyufeng
 * @Date: 2019/4/23
 */

@Component
public class TestScheduler {

    @Scheduled(cron = "*/5 * * * * ?")
    public void testScheduler() {
        System.out.println("testScheduler success");
    }
}
