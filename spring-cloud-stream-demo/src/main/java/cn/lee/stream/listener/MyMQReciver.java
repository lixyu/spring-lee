package cn.lee.stream.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

/**
 * @author : Lee
 * @date : 2019/6/19
 */

@Component
@Slf4j
@EnableBinding(Processor.class)
public class MyMQReciver {

    @StreamListener(Processor.INPUT)
    public void process(String message) {
        log.info("hahahah : " + message);
        System.out.println("hahahah : " + message);
    }
}

