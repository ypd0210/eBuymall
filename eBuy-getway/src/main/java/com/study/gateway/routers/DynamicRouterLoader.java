package com.study.gateway.routers;

import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * @program: eBuymall
 * @description: 动态路由配置
 * @author: 心念
 * @create: 2024-09-07 15:51
 **/
@Slf4j
@Component //在springboot项目初始化的时候自动装配为bean
@RequiredArgsConstructor//根据需要自动注入,代替@AutoWired注解 必须的构造函数
public class DynamicRouterLoader {

    private final NacosConfigManager nacosConfigManager;
    private final RouteDefinitionWriter routeDefinitionWriter;

    private final String dataId = "gateway-routes.json";
    private final String groupId = "DEFAULT_GROUP";

    private final Set<String> routeIds = new HashSet<String>();

    @PostConstruct //标记了@PostConstruct的方法会在Bean构造完成后自动执行方法的逻辑。
    public void initRouterConfigListener() throws NacosException {
        String configInfo = nacosConfigManager.getConfigService()
                .getConfigAndSignListener(dataId, groupId, 5000, new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        //监听到配置变更的时候刷新路由表
                        updateConfigInfo(configInfo);
                    }
                });

        //第一次监听到路由表信息的时候刷新路由信息
        updateConfigInfo(configInfo);
    }

    //刷新路由信息
    public void updateConfigInfo(String configInfo){
        log.debug("监听到路由配置信息:{}",configInfo);
        //解析配置信息
        List<RouteDefinition> routeDefinitions = JSONUtil.toList(configInfo, RouteDefinition.class);
        //删除旧的路由表信息
        for (String routeId : routeIds) {
            routeDefinitionWriter.delete(Mono.just(routeId));
        }
        routeIds.clear();
        //更新路由表
        for (RouteDefinition routeDefinition : routeDefinitions) {
            //更新路由
            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            //记录路由表信息
            routeIds.add(routeDefinition.getId());
        }
    }
}
