package com.example.demo;

import com.example.demo.config.AmqpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lee
 * @date 2019-07-29 17:33
 */
@Slf4j
@Component
public class Publisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(String message) {
        try {
            rabbitTemplate.convertAndSend(AmqpConfig.LIND_EXCHANGE, AmqpConfig.LIND_QUEUE,message);
            log.info("message:"+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTest(String message) {
        try {
            rabbitTemplate.convertAndSend(AmqpConfig.LIND_FANOUT_EXCHANGE, "",message);
            log.info("message:"+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
