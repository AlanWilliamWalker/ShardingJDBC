package com.example.readwrite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.readwrite.mapper")
public class ReadwriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadwriteApplication.class, args);
    }

}
