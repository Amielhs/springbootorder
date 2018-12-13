package com.tt.springbootorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.tt.springbootorder.dao")
public class SpringBootOrderApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOrderApp.class, args);
    }
}
