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
     * 图片验证码前缀
     */
    public static final String IMAGE_CODE_KEY_PREFIX = "code:image:";

    /**
     * 登录短信验证码
     */
    public static final String LOGIN_SMS_CODE_KEY_PREFIX = "login:code:sms:";

}
