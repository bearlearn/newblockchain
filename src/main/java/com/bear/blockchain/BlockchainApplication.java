package com.bear.blockchain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication

@EnableFeignClients

@MapperScan("com.bear.blockchain.dao")

@EnableAsync

@EnableWebSocket

@EnableScheduling

public class BlockchainApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlockchainApplication.class, args);
    }

}
