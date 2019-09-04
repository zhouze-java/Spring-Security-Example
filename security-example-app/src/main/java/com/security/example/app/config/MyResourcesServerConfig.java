package com.security.example.app.config;

import com.security.example.app.authentication.social.config.OpenIdAuthenticationSecurityConfig;
import com.security.example.core.authentication.sms.config.SmsAuthenticationSecurityConfig;
import com.security.example.core.config.SecurityConstants;
import com.security.example.core.config.SecurityProperties;
import com.security.example.core.config.authorize.AuthorizeConfigManager;
import com.security.example.core.validate.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author 周泽
 * @date Create in 17:04 2019/8/26
 * @Description 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class MyResourcesServerConfig extends ResourceServerConfigurerAdapter {

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

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // web网页登录的配置
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);

        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsAuthenticationSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .apply(securitySocialConfigurer)
                .and()
                .csrf().disable()
        ;

        authorizeConfigManager.config(http.authorizeRequests());

    }
}
