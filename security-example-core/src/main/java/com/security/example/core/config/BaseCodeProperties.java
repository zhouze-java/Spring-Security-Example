package com.security.example.core.config;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 22:30 2019/8/12
 * @Description 验证码配置的父类
 */
@Data
public class BaseCodeProperties {
    /**
     * 验证码字符个数
     */
    private int codeCount = 4;

    /**
     * 过期秒数
     */
    private int expireIn = 60;

    /**
     * 要拦截的请求 逗号隔开
     */
    private String urls;
}
