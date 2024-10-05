package com.study.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: eBuymall
 * @description: 购物车配置信息
 * @author: 心念
 * @create: 2024-09-06 10:28
 **/
@Data
@Component
@ConfigurationProperties(prefix = "ebuy.cart")
public class CartProperties {
    private Integer MAX_CART_SIZE;
}
