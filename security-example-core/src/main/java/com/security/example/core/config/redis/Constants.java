package com.security.example.core.config.redis;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 18:39 2019/8/10
 * @Description
 */
@Data
public class Constants {

    /**
     * 验证码前缀
     */
    public static final String VALIDATE_CODE_KEY_PREFIX = "code:";

    /**
     * 第三方用户信息验证码前缀
     */
    public static final String SOCIAL_USER_INFO_KEY_PREFIX = "social:connection:";
}
