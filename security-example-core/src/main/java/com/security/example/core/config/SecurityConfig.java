package com.security.example.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 周泽
 * @date Create in 16:30 2019/8/7
 * @Description
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityConfig {
}
