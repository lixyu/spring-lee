package cn.lee.stream.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @author Lee
 * @date 2019-10-11 15:22
 */
@Component
public interface RabbitChannel {
    String OUTPUT = "test-output";
    String INPUT = "test-input";

    @Output(OUTPUT)
    MessageChannel output() throws Exception;

    @Input(INPUT)
    SubscribableChannel input();
}
