package com.angel.user.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2018/6/7.
 */
@Getter
public enum ResultEnum {
    USER_NOT_EXIST(1,"用户不存在"),
    ROLE_ERROR(2, "角色权限有误")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
