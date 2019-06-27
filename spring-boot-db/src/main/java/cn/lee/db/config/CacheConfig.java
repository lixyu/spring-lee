package cn.lee.db.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author Isaac
 * @Date: 11/22/2018 11:27
 * @Description:
 */
@EnableCaching(proxyTargetClass = true)
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //default信息缓存配置
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(2)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_default_");
        //rmCloud信息缓存配置
        RedisCacheConfiguration h8 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(8)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_h8_");
        RedisCacheConfiguration d1 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_d1_");
        RedisCacheConfiguration d7 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(7)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_d7_");
        RedisCacheConfiguration d30 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(30)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_d30_");
        RedisCacheConfiguration m1 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_m1_");
        RedisCacheConfiguration m5 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_m5_");
        RedisCacheConfiguration m10 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_m10_");
        RedisCacheConfiguration m15 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(15)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_m15_");
        RedisCacheConfiguration m30 = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())).disableCachingNullValues().prefixKeysWith("boltloan_m30_");

        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new LinkedHashMap<String, RedisCacheConfiguration>(8) {
            private static final long serialVersionUID = -1543764675265322850L;

            {
                put("8h", h8);
                put("1d", d1);
                put("7d", d7);
                put("30d", d30);
                put("1m", m1);
                put("5m", m5);
                put("10m", m10);
                put("15m", m15);
                put("30m", m30);
            }
        };

        return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory), defaultCacheConfiguration, redisCacheConfigurationMap);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        RedisSerializer jackson2JsonRedisSerializer = jsonSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    private RedisSerializer jsonSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    /*@Bean
    public RedisUtils redisUtils(RedisTemplate<String, Object> template) {
        return new RedisUtils(template);
    }*/
}
