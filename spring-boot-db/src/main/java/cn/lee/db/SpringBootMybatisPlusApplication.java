package cn.lee.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


//@EnableRedisHttpSession
@EnableAsync(proxyTargetClass = true)
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication
public class SpringBootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusApplication.class, args);
    }

}
