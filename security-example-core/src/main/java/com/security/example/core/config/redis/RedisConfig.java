package com.security.example.core.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 周泽
 * @date Create in 18:37 2019/8/10
 * @Description Redis配置类
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setStringSerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(redisObjectSerializer);

        return redisTemplate;
    }
}
