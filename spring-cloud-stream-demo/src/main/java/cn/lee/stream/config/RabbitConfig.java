package cn.lee.stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Lee
 * @date : 2019/6/19
 */
@Configuration
@EnableBinding({RabbitChannel.class, Processor.class})
public class RabbitConfig {
}
