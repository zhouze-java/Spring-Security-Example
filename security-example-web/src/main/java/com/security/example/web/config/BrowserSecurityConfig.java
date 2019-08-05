package com.security.example.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 周泽
 * @date Create in 16:16 2019/8/5
 * @Description 浏览器的security配置
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // formLogin 表示表单认证
        http.formLogin()
                .and()
                // 授权请求. anyRequest 就表示所有的请求都需要权限认证
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
