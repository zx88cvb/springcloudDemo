package com.angel.service;

import com.angel.dto.OrderDTO;

/**
 * Created by Administrator on 2018/6/6.
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
