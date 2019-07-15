package cn.lee.nosql.config;

import cn.lee.nosql.interceptor.CacheKeyGenerator;
import cn.lee.nosql.interceptor.LockKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Lee
 * @date 2019-07-09 17:42
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }
}
