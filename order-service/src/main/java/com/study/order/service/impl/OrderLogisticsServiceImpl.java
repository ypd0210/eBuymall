package com.study.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.order.domain.po.OrderLogistics;
import com.study.order.mapper.OrderLogisticsMapper;
import com.study.order.service.IOrderLogisticsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsMapper, OrderLogistics> implements IOrderLogisticsService {

}
