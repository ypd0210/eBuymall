package com.study.api.config;

import com.study.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @program: hmall
 * @description: 默认fgin全局配置
 * @author: 心念
 * @create: 2024-08-30 14:33
 **/
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor serviceInterceptor(){
        return requestTemplate -> {
            Long userId = UserContext.getUser();
            if (userId == null){
                throw new RuntimeException("用户信息为空");
            }
            requestTemplate.header("userInfo",userId.toString());
        };
    }
}
