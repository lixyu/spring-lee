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
    String OUTPUT_3 = "test3-output";
    String INPUT_3 = "test3-input";
    String INPUT = "test-input";
    String DELAY_OUTPUT = "delay-output";
    String DELAY_INPUT = "delay-input";

    @Output(DELAY_OUTPUT)
    MessageChannel delayOutput();
    @Input(DELAY_INPUT)
    SubscribableChannel dealyInput();

    @Output(OUTPUT)
    MessageChannel output() throws Exception;

    @Output(OUTPUT_3)
    MessageChannel output3() throws Exception;

    @Input(INPUT)
    SubscribableChannel input();
    @Input(INPUT_3)
    SubscribableChannel input3();
}
