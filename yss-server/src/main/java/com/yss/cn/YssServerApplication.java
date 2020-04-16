package com.yss.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yss.persistence.dao.pc")//扫描mapper
public class YssServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YssServerApplication.class, args);
    }

}
