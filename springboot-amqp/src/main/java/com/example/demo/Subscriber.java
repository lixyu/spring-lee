package com.example.demo;

import com.example.demo.config.AmqpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Lee
 * @date 2019-07-29 17:34
 */
@Slf4j
@Component
public class Subscriber {
    @RabbitListener(queues = AmqpConfig.LIND_DEAD_QUEUE)
    public void customerSign(String data) {
        try {

            log.info("从队列拿到数据 ：{}", data);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
