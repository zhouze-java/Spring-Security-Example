package com.security.example.core.config.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author 周泽
 * @date Create in 21:19 2019/9/4
 * @Description 权限配置提供者
 */
public interface AuthorizeConfigProvider {

    /**
     * 权限配置
     *
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
