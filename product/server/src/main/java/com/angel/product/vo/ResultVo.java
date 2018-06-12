package com.angel.product.vo;

import com.angel.product.enums.ResponseCode;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/28.
 */
@Data
public class ResultVo<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

    public ResultVo(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultVo<T> createBySuccess(T data){
        return new ResultVo<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ResultVo<T> createBySuccess(String msg,T data){
        return new ResultVo<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }
}
