package com.angel.exception;

import com.angel.enums.ResultEnum;

/**
 * Created by Administrator on 2018/6/7.
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
