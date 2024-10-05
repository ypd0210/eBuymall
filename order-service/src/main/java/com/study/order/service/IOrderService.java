package com.study.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.order.domain.dto.OrderFormDTO;
import com.study.order.domain.po.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);
}
