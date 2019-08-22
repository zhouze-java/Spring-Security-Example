package com.security.example.web.config;

import com.security.example.core.authentication.AbstractChannelSecurityConfig;
import com.security.example.core.authentication.sms.config.SmsAuthenticationSecurityConfig;
import com.security.example.core.config.SecurityConstants;
import com.security.example.core.config.SecurityProperties;
import com.security.example.core.validate.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author 周泽
 * @date Create in 16:16 2019/8/5
 * @Description 浏览器的security配置
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer securitySocialConfigurer;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // web网页登录的配置
        applyPasswordAuthenticationConfig(http);

        http
                    .apply(validateCodeSecurityConfig)
                .and()
                    .apply(smsAuthenticationSecurityConfig)
                .and()
                    .apply(securitySocialConfigurer)
                .and()
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                    .authorizeRequests()
                    // 匹配的是登录页的话放行
                    .antMatchers(
                            SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                            securityProperties.getBrowser().getLoginPage(),
                            SecurityConstants.DEFAULT_SIGN_UP_URL,
                            securityProperties.getBrowser().getSignUpPage(),
                            SecurityConstants.GET_SOCIAL_USER_URL,
                            "/user/register",
                            "/test/pwd/*",
                            "/test/match/*"
                    )
                    .permitAll()
                    // 授权请求. anyRequest 就表示所有的请求都需要权限认证
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable()
                ;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/img/**");
    }

}

