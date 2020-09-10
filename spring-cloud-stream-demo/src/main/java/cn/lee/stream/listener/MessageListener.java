package cn.lee.stream.listener;

import cn.lee.stream.mq.RabbitChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author : Lee
 * @date : 2019/6/19
 */
@Slf4j
@Component
public class MessageListener {

    @StreamListener(Sink.INPUT)
    public void handle(String person) {
        System.out.println("Received2: " + person);
    }

    @StreamListener(RabbitChannel.INPUT_3)
    public void handle3(String person) {
        System.out.println("Received3: " + person);
    }


    @StreamListener(RabbitChannel.INPUT)
    @SendTo(RabbitChannel.OUTPUT_3)
    public String test(String person) {
        System.out.println("Received1: " + person);
        return "Hello,"+person;
    }

    @StreamListener(RabbitChannel.DELAY_INPUT)
    public void delayInput(String delayMessage){

        log.info("延时消费:{}",delayMessage);

    }

}
