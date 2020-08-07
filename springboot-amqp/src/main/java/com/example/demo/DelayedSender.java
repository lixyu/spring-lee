package com.example.demo;

import com.example.demo.config.DelayedConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Lee
 * @date : 2020-08-06
 */
@Component
@RequiredArgsConstructor
public class DelayedSender {

    private final AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("发送时间:" + sf.format(new Date()));

        rabbitTemplate.convertAndSend(DelayedConfig.EXCHANGE_NAME, DelayedConfig.QUEUE_NAME, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //message.getMessageProperties().setHeader("x-delay", 3000);
                message.getMessageProperties().setDelay(6000);
                return message;
            }
        });
    }
}
