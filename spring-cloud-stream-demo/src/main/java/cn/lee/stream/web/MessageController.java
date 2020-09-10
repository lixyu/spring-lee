package cn.lee.stream.web;

import cn.lee.stream.mq.Person;
import cn.lee.stream.mq.RabbitChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Lee
 * @date : 2019/6/19
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final RabbitChannel rabbitChannel;
    private final Source source;
    private final Processor processor;


    @GetMapping("/source/{message}")
    public String source(@PathVariable("message") String message) throws Exception {
        Person person = new Person(message);
        source.output().send(MessageBuilder.withPayload(person).build());
        return "success";
    }

    @GetMapping("/send")
    public void send(@RequestParam String message) {
        processor.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/test")
    public void test(@RequestParam String message) {
        processor.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/rabbit/{message}")
    public String sendRabbit(@PathVariable("message") String message) throws Exception {
        rabbitChannel.output().send(MessageBuilder.withPayload(message).build());
        return "success";
    }

    @GetMapping("/stream/delay/{message}")
    public String streamDelay(@PathVariable("message") String message) throws Exception {
        //延时10s
        rabbitChannel.delayOutput().send(MessageBuilder.withPayload(message).setHeader("x-delay",10000).build());

        return "success";
    }
}
