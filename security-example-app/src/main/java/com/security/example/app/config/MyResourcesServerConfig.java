package com.security.example.app.config;

import com.security.example.core.authentication.sms.config.SmsAuthenticationSecurityConfig;
import com.security.example.core.config.SecurityConstants;
import com.security.example.core.config.SecurityProperties;
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
                .apply(securitySocialConfigurer)
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
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        "/user/register"
                )
                .permitAll()
                // 授权请求. anyRequest 就表示所有的请求都需要权限认证
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
        ;
    }
}
