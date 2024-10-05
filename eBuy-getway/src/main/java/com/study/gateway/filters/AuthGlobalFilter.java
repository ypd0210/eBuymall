package com.study.gateway.filters;

import com.study.common.exception.UnauthorizedException;
import com.study.gateway.config.AuthProperties;
import com.study.gateway.util.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @program: eBuymall
 * @description: 权限校验注解
 * @author: 心念
 * @create: 2024-09-04 13:38
 **/
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter , Ordered {
    private final AuthProperties authProperties;
    private final JwtTool jwtTool;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取request
        ServerHttpRequest request = exchange.getRequest();
        //判断是否需要权限校验
        if (isExclude(request.getURI().getPath())){
            //放行
            return chain.filter(exchange);
        }
        //获取token
        String token = null;
        List<String> headers = request.getHeaders().get("Authorization");
        if (headers != null && !headers.isEmpty()){
            //未登录
            token = headers.get(0);
        }
        //校验并解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        }catch (UnauthorizedException e){
            //拦截并返回响应状态码
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            Mono<Void> voidMono = response.setComplete();
            return voidMono;
        }

        //传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange serverWebExchange = exchange.mutate()
                .request(builder -> builder.header("userInfo", userInfo))
                .build();
        //放行
        return chain.filter(serverWebExchange);

    }

    private boolean isExclude(String path) {
        for (String excludePath : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(excludePath, path)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
