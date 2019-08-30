package com.security.example.app.config;

import com.security.example.core.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
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
    @ConditionalOnProperty(prefix = "core.security.oAuth2", name="tokenStore", havingValue="redis")
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(connectionFactory);
    }

    @Configuration
    @ConditionalOnProperty(prefix = "core.security.oAuth2", name="tokenStore", havingValue="jwt", matchIfMissing = true)
    public static class JwtTokenConfig{

        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            // token生成中的一些处理
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(securityProperties.getOAuth2().getJwtTokenSignKey());
            return converter;
        }

        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer(){
            return new MyJwtTokenEnhancer();
        }

    }
}
