package com.psbc.wyk.dangjian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.psbc.wyk.dangjian.dao.repo.*")
public class DangjianApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangjianApplication.class, args);
    }

}
