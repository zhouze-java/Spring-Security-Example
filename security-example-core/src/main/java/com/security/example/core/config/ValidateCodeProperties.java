package com.security.example.core.config;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 12:40 2019/8/11
 * @Description 验证码配置类
 */
@Data
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties imageCode = new ImageCodeProperties();


}
