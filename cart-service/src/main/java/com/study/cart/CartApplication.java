package com.study.cart;

import com.study.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: ebuyMall
 * @description: 购物车服务启动项
 * @author: 心念
 * @create: 2024-08-29 15:22
 **/
@EnableFeignClients(basePackages = "com.study.api.client",defaultConfiguration = DefaultFeignConfig.class) //,defaultConfiguration = DefaultFeignConfig.class 调试开关
@MapperScan("com.study.cart.mapper")
@SpringBootApplication
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}
