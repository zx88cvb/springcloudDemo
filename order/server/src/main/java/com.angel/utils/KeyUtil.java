package com.angel.utils;

import java.util.Random;

/**
 * Created by Administrator on 2018/6/6.
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */

    public static synchronized String getUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
