package com.study.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: ebuyMall
 * @description: 商品类启动项
 * @author: 心念
 * @create: 2024-08-29 14:13
 **/
@EnableFeignClients(basePackages = "com.study.api.client")
@MapperScan("com.study.item.mapper")
@SpringBootApplication
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
