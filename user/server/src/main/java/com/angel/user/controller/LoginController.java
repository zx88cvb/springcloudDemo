package com.angel.user.controller;

import com.angel.user.constant.CookieConstant;
import com.angel.user.constant.RedisConstant;
import com.angel.user.dataobject.UserInfo;
import com.angel.user.enums.ResponseCode;
import com.angel.user.enums.ResultEnum;
import com.angel.user.enums.RoleEnum;
import com.angel.user.service.UserService;
import com.angel.user.utils.CookieUtil;
import com.angel.user.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/7/5.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("buyer")
    public ResultVo buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        // openid和数据库匹配
        UserInfo userInfo = userService.findByOpenid(openid);

        if(userInfo == null){
            return ResultVo.createByError(ResponseCode.ERROR.getDesc());
        }
        //判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()){
            return ResultVo.createByError(ResultEnum.ROLE_ERROR.getMessage());
        }
        //设置cookie
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.EXPIRE);

        return ResultVo.createBySuccess(ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("seller")
    public ResultVo seller(@RequestParam("openid") String openid,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        //判断是否已登录
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);


        if(cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){
            return ResultVo.createBySuccess(ResponseCode.SUCCESS.getDesc());
        }
        // openid和数据库匹配
        UserInfo userInfo = userService.findByOpenid(openid);

        if(userInfo == null){
            return ResultVo.createByError(ResponseCode.ERROR.getDesc());
        }
        //判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()){
            return ResultVo.createByError(ResultEnum.ROLE_ERROR.getMessage());
        }
        String token = UUID.randomUUID().toString();
        //往redis存入openId
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),
                openid,
                CookieConstant.EXPIRE,
                TimeUnit.SECONDS);
        //设置cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        return ResultVo.createBySuccess(ResponseCode.SUCCESS.getDesc());
    }
}
