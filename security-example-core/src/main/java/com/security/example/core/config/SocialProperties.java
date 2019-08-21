package com.security.example.core.config;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 11:09 2019/8/17
 * @Description Social配置类
 */
@Data
public class SocialProperties {

    /**
     * social 要拦截的请求地址
     */
    private String filterProcessesUrl = "/auth";

    /**
     * QQ的配置
     */
    private QQProperties qq = new QQProperties();

    /**
     * 微信的配置
     */
    private WeChatProperties weChat = new WeChatProperties();
}
