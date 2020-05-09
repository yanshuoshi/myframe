package com.yss.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan//druid
@EnableAsync//异步
@EnableTransactionManagement//事务
public class YssServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YssServerApplication.class, args);
    }
}
