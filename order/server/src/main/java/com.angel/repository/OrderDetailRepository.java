package com.angel.repository;

import com.angel.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-10 16:12
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 根据orderId查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
