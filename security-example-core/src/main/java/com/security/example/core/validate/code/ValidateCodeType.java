package com.security.example.core.validate.code;

import com.security.example.core.config.SecurityConstants;

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
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    /**
     * 图片验证码
     */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    },
    ;

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();

}
