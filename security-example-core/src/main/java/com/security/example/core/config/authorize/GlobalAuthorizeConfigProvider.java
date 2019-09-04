package com.security.example.core.config.authorize;

import com.security.example.core.config.SecurityConstants;
import com.security.example.core.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 周泽
 * @date Create in 21:21 2019/9/4
 * @Description 通用的权限配置
 */
@Component
public class GlobalAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getLoginPage(),
                SecurityConstants.DEFAULT_SIGN_UP_URL,
                securityProperties.getBrowser().getSignUpPage(),
                SecurityConstants.GET_SOCIAL_USER_URL,
                securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                SecurityConstants.DEFAULT_APP_SIGNUP_URL
        )
                .permitAll();

    }

}
