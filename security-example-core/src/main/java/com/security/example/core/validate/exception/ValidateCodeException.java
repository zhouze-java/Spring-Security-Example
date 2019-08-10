package com.security.example.core.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 周泽
 * @date Create in 23:45 2019/8/10
 * @Description 验证码验证异常
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
