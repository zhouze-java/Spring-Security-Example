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
     * QQ的配置
     */
    private QQProperties qq = new QQProperties();
}