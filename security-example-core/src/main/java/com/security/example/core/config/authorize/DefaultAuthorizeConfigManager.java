package com.security.example.core.config.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author 周泽
 * @date Create in 21:27 2019/9/4
 * @Description
 */
@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager {

    /**
     * 收集系统中的所有的{@link AuthorizeConfigProvider}实现
     */
    @Autowired
    private Set<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            authorizeConfigProvider.config(config);
        }
        // 除了上面配置的，其他的都需要登录后才能访问
        config.anyRequest().authenticated();
    }

}
