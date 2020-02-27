package com.example.demo;

import com.example.demo.config.AmqpConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Lee
 * @date 2019-07-29 17:34
 */
@Slf4j
@Component
public class Subscriber {
    @RabbitListener(queues = AmqpConfig.LIND_QUEUE)
    public void customerSign(String data) {
        try {

            log.info("从队列拿到数据 ：{}", data);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @RabbitListener(queues = AmqpConfig.TEST_QUEUE)
    public void listerTest(Message message, Channel channel) throws IOException {
        SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
        Object object = simpleMessageConverter.fromMessage(message);
        String json = String.valueOf(object);
        try {
            //Thread.sleep(5000);
            log.info("从队列拿到数据:{}", json);

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
