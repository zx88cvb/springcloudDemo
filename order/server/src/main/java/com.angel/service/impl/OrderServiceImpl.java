package com.angel.service.impl;

import com.angel.dataobject.OrderDetail;
import com.angel.dataobject.OrderMaster;
import com.angel.dto.OrderDTO;
import com.angel.enums.OrderStatus;
import com.angel.enums.PayStatusEnum;
import com.angel.product.client.ProductClient;
import com.angel.product.common.DecreaseStockInput;
import com.angel.product.common.ProductInfoOutput;
import com.angel.repository.OrderDetailRepository;
import com.angel.repository.OrderMasterRepository;
import com.angel.service.OrderService;
import com.angel.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/6/6.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String OrderId = KeyUtil.getUniqueKey();
         //查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().
                stream().
                map(OrderDetail::getProductId).
                collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);
        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetailItem : orderDTO.getOrderDetailList()){
            for(ProductInfoOutput productInfoItem : productInfoList){
                if(productInfoItem.getProductId().equals(orderDetailItem.getProductId())){
                    //单价*数量
                    orderAmount = productInfoItem.getProductPrice().
                            multiply(BigDecimal.valueOf(orderDetailItem.getProductQuantity())).
                            add(orderAmount);
                    BeanUtils.copyProperties(productInfoItem, orderDetailItem);
                    orderDetailItem.setOrderId(OrderId);
                    orderDetailItem.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetailItem);
                }
            }

        }
         //扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().
                stream().
                map(e -> new DecreaseStockInput(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);
         // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(OrderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
