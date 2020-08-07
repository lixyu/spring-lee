package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

/**
 * @author : Lee
 * @date : 2020-08-06
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelayedTest {

    @Autowired
    private DelayedSender sender;

    @Test
    public void Test() throws InterruptedException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        sender.send("Hi Admin.");
        Thread.sleep(5 * 1000); //等待接收程序执行之后，再退出测试
    }
}
