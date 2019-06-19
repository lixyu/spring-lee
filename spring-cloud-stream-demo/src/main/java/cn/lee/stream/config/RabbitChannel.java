package cn.lee.stream.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @author : Lee
 * @date : 2019/6/19
 */
@Component
public interface RabbitChannel {
    String OUTPUT = "test-sender";
    String INPUT = "test-sender1";

    @Output(OUTPUT)
    MessageChannel output() throws Exception;

    @Input(INPUT)
    SubscribableChannel input();
}
