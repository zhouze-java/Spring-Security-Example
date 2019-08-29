package com.security.example.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author 周泽
 * @date Create in 18:04 2019/8/29
 * @Description
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(connectionFactory);
    }
}
