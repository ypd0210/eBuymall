package com.study.order;

import com.study.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: hmall
 * @description: 订单服务启动项
 * @author: 心念
 * @create: 2024-08-31 14:19
 **/
@EnableFeignClients(basePackages = "com.study.api.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.study.order.mapper")
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
