package com.security.example.core.validate.code;

import java.io.Serializable;

/**
 * @author 周泽
 * @date Create in 11:41 2019/8/13
 * @Description 验证码类型枚举
 */
public enum ValidateCodeType implements Serializable {

    /**
     * 短信验证码
     */
    SMS,

    /**
     * 图片验证码
     */
    IMAGE,
    ;

}
