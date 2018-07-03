package com.angel.repository;

import com.angel.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/7/3.
 */
public interface UserInfoRepostory extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openId);
}
