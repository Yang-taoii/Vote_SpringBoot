package com.yangtao.vote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yangtao.vote.mapper")
public class VoteDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteDemoApplication.class, args);
    }

}
