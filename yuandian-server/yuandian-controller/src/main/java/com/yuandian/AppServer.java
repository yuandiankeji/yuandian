package com.yuandian;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * @author: luyufeng
 * @Date: 2019/3/25
 * 短链接服启动
 */
@SpringBootApplication(exclude = {DataSourceTransactionManagerAutoConfiguration.class})
@MapperScan("com.yuandian.mapper")
public class AppServer {

    public static void main(String[] args) {
        SpringApplication.run(AppServer.class, args);
    }
}
