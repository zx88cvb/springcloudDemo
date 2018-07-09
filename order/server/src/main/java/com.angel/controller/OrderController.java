package com.angel.controller;

import com.angel.VO.ResultVo;
import com.angel.converter.OrderForm2OrderDTOConverter;
import com.angel.dto.OrderDTO;
import com.angel.enums.ResultEnum;
import com.angel.exception.OrderException;
import com.angel.form.OrderForm;
import com.angel.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Administrator on 2018/6/6.
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 参数检验
     * 查询商品信息(调用商品服务)
     * 计算总价
     * 扣库存(调用商品服务)
     * 订单入库
     */
    @PostMapping("create")
    public ResultVo create(@Valid OrderForm orderForm,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("创建订单参数不正确,orderForm={}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("创建订单购物车信息为空,orderForm={}",orderForm);
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        return ResultVo.createBySuccess(result.getOrderId());
    }

    /**
     * 完成订单(只能卖家操作)
     * @param orderId
     * @return
     */
    @PostMapping("/finish")
    public ResultVo<OrderDTO> finish(String orderId){
        return ResultVo.createBySuccess(orderService.finish(orderId));
    }
}
