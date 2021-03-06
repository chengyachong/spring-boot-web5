package com.dfe.sp.bigscreen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dfe.sp.bigscreen.mapper")
public class BigScreenApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigScreenApplication.class, args);
    }

}
