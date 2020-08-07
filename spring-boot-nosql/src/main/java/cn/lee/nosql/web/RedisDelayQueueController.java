package cn.lee.nosql.web;

import cn.lee.nosql.service.RedisDelayQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lee
 * @date : 2020-01-15
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class RedisDelayQueueController {

    private final RedisDelayQueue redisDelayQueue;

    @GetMapping("/product")
    public void produce() {

        for (int i = 0; i < 30; i++) {

            redisDelayQueue.produce("topic"+i%3 , "hello message"+i , new Date(System.currentTimeMillis()+i*10000));

        }

    }

    @GetMapping("/consumer")
    public void consumer() throws InterruptedException {

        redisDelayQueue.consumer("topic0", (msg)->{
            log.info("topic【{}】收到消息：{}","topic0",msg);
            return true;
        });
        redisDelayQueue.consumer("topic1", (msg)->{
            log.info("topic【{}】收到消息：{}","topic1",msg);
            return true;
        });

        redisDelayQueue.consumer("topic2", (msg) -> {
            log.info("topic【{}】收到消息：{}", "topic2", msg);
            return true;
        });

        TimeUnit.MINUTES.sleep(10);

    }

}