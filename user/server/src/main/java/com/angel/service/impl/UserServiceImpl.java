package com.angel.service.impl;

import com.angel.dataobject.UserInfo;
import com.angel.repository.UserInfoRepostory;
import com.angel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/3.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepostory userInfoRepostory;

    @Override
    public UserInfo findByOpenid(String openId) {
        return userInfoRepostory.findByOpenid(openId);
    }
}
