package com.security.example.core.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author 周泽
 * @date Create in 17:09 2019/8/21
 * @Description 微信登录配置
 */
@Data
public class WeChatProperties  extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "weixin";

}
