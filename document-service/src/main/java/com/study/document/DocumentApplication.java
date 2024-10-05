package com.study.document;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: ebuyMall
 * @description: 用户服务启动类
 * @author: 心念
 * @create: 2024-08-30 14:46
 **/
@EnableFeignClients(basePackages = "com.study.api.client")
@MapperScan("com.study.document.mapper")
@SpringBootApplication
public class DocumentApplication {
    public static void main(String[] args) {
        SpringApplication.run(DocumentApplication.class,args);
    }
}
