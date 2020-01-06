package cn.lee.stream.listener;

import cn.lee.stream.mq.RabbitChannel;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * @author : Lee
 * @date : 2019/6/19
 */
@Component
public class MessageListener {

    @StreamListener(Sink.INPUT)
    public void handle(String person) {
        System.out.println("Received2: " + person);
    }


    @StreamListener(RabbitChannel.INPUT)
    public void test(String person) {
        System.out.println("Received1: " + person);
    }

}
