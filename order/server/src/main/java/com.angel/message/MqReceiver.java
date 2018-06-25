package com.angel.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收mq消息
 * Created by Administrator on 2018/6/25.
 */
@Slf4j
@Component
public class MqReceiver {

    //@RabbitListener(queues = "myQueue")

    //自动创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))

    //自动创建exchange和Queue
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message){
        log.info("MqReceiver:{}",message);
    }


    /**
     * 数码供应商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computerOrder"),
            key = "computer",
            exchange = @Exchange("myOrder")
    ))
    public void processComputer(String message){
        log.info("computer MqReceiver:{}",message);
    }



    /**
     * 水果供应商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitOrder"),
            key = "fruit",
            exchange = @Exchange("myOrder")
    ))
    public void processFruit(String message){
        log.info("fruit MqReceiver:{}",message);
    }
}
