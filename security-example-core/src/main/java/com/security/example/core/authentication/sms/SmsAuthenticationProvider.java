package com.security.example.core.authentication.sms;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @author 周泽
 * @date Create in 22:53 2019/8/13
 * @Description 短信登录最终校验器
 */
@Data
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;

        // 根据手机号去取密码
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        // 如果找到用户信息了,就给一个新的认证过的token
        SmsAuthenticationToken SmsAuthenticationSuccessToken = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());
        // 请求的详细信息从旧的哪里拿出来放进去
        SmsAuthenticationSuccessToken.setDetails(authenticationToken.getDetails());
        return SmsAuthenticationSuccessToken;
    }

    /**
     * 判断传进来的token (authentication对象) 是否支持处理
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
