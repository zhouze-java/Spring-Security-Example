package com.security.example.core.config;

import com.security.example.core.validate.code.image.ValidateCode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 周泽
 * @date Create in 16:21 2019/8/7
 * @Description 配置总类
 */
@Data
@ConfigurationProperties(prefix = "core.security")
public class SecurityProperties {

    /**
     * 浏览器配置
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * social 的配置
     */
    private SocialProperties social = new SocialProperties();

    /**
     * oAuth2的配置
     */
    private OAuth2Properties oAuth2 = new OAuth2Properties();
}
