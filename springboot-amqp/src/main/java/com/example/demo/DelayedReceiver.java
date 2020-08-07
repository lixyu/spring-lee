package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Lee
 * @date : 2020-08-06
 */

@Component
@RabbitListener(queues = "delayed.goods.order")
public class DelayedReceiver {
    @RabbitHandler
    public void process(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("接收时间:" + sdf.format(new Date()));
        System.out.println("消息内容:" + msg);
    }
}
