package com.yuandian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: luyufeng
 * @Date: 2019/3/25
 * 定时任务工程
 */
@SpringBootApplication(exclude = {DataSourceTransactionManagerAutoConfiguration.class})
@EnableScheduling
public class SchedulerAppServer {


    public static void main(String[] args) {
        SpringApplication.run(SchedulerAppServer.class, args);
    }
}
