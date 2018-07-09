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

    /**
     * 完结订单(只能卖家操作)
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
