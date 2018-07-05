package com.angel.user.service;

import com.angel.user.dataobject.UserInfo;

/**
 * Created by Administrator on 2018/7/3.
 */
public interface UserService {

    /**
     * 通过openId查询
     * @param openId
     * @return
     */
    UserInfo findByOpenid(String openId);
}
