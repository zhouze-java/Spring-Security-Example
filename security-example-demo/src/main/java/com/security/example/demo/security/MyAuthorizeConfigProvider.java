package com.security.example.demo.security;

import com.security.example.core.config.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 周泽
 * @date Create in 22:44 2019/9/4
 * @Description 自定义权限拦截
 */
@Component
public class MyAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/user/register")
                .permitAll()
                .antMatchers("/users")
                .hasRole("ADMIN");

    }

}
