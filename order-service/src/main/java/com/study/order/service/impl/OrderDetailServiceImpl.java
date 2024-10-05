package com.study.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.order.domain.po.OrderDetail;
import com.study.order.mapper.OrderDetailMapper;
import com.study.order.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author 心念
 * @since 2023-05-05
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
