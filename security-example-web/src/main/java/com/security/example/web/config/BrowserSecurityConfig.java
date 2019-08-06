package com.security.example.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 周泽
 * @date Create in 16:16 2019/8/5
 * @Description 浏览器的security配置
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // formLogin 表示表单认证
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/from")
                .and()
                .authorizeRequests()
                // 匹配的是登录页的话放行
                .antMatchers("/authentication/require", "/login.html").permitAll()
                // 授权请求. anyRequest 就表示所有的请求都需要权限认证
                .anyRequest().authenticated()
                .and().csrf().disable();

    }
}
