package com.security.example.core.config.redis;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
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

    @Primary
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        if (cluster != null) {
            RedisClusterConfiguration configuration = new RedisClusterConfiguration(cluster.getNodes());
            return new LettuceConnectionFactory(configuration);
        } else {
            LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
            connectionFactory.setPassword(redisProperties.getPassword());
            return connectionFactory;
        }
    }
}
