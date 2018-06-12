package com.angel.product.exception;

import com.angel.product.enums.ResultEnum;

/**
 * Created by Administrator on 2018/6/11.
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
