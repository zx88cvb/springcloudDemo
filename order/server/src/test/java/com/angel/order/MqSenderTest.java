package com.angel.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 发送mq消息测试
 * Created by Administrator on 2018/6/25.
 */
@Component
public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        String message = new Date().toString();
        amqpTemplate.convertAndSend("myQueue","now time is --------------------:"+message);
    }

    @Test
    public void sendOrder(){
        String message = new Date().toString();
        amqpTemplate.convertAndSend("myOrder", "computer", "now time is --------------------:"+message);
    }
}
