package com.security.example.core.config;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 12:11 2019/8/11
 * @Description 图形验证码配置
 */
@Data
public class ImageCodeProperties extends BaseCodeProperties{
    /**
     * 图片的宽度
     */
    private int width = 160;

    /**
     *  图片的高度
     */
    private int height = 40;


}
