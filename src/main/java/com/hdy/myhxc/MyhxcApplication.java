package com.hdy.myhxc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hdy.myhxc.mapper")
public class MyhxcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyhxcApplication.class, args);
    }

}
