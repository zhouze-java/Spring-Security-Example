package com.security.example.core.authentication.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author 周泽
 * @date Create in 23:08 2019/8/27
 * @Description 后处理器
 */
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * SocialAuthenticationFilter 请求处理
     * @param socialAuthenticationFilter
     */
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
