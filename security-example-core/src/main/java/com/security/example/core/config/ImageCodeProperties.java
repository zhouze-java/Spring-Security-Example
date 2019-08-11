package com.security.example.core.config;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 12:11 2019/8/11
 * @Description 图形验证码配置
 */
@Data
public class ImageCodeProperties {
    /**
     * 图片的宽度
     */
    private int width = 160;

    /**
     *  图片的高度
     */
    private int height = 40;

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
