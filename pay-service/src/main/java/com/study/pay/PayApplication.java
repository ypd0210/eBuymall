package com.study.pay;

import com.study.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: hmall
 * @description: 支付服务启动项
 * @author: 心念
 * @create: 2024-08-31 23:19
 **/
@EnableFeignClients(basePackages = "com.study.api.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.study.pay.mapper")
@SpringBootApplication
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class,args);
    }
}
