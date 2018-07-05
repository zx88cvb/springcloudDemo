package com.angel.user.service.impl;

import com.angel.user.dataobject.UserInfo;
import com.angel.user.repository.UserInfoRepostory;
import com.angel.user.service.UserService;
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
