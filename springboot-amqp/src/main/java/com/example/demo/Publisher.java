package com.example.demo;

import com.example.demo.config.AmqpConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Lee
 * @date 2019-07-29 17:33
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Publisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(String message) {
        try {
            rabbitTemplate.convertAndSend(AmqpConfig.LIND_EXCHANGE, AmqpConfig.LIND_QUEUE,message,new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    //message.getMessageProperties().setHeader("x-delay", 30000);
                    message.getMessageProperties().setDelay(30000);
                    return message;
                }
            });
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
