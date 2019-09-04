package com.security.example.core.config.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author 周泽
 * @date Create in 21:27 2019/9/4
 * @Description 权限配置管理器
 */
public interface AuthorizeConfigManager {

    /**
     * 权限配置
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
