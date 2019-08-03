package com.security.example.demo.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author 周泽
 * @date Create in 10:22 2019/8/3
 * @Description 手机号验证具体逻辑
 */
@Slf4j
public class PhoneNoValidator implements ConstraintValidator<PhoneNo, Object> {

    /**
     * 手机号码正则表达式
     */
    public static final String REG_PHONE = "^((13[0-9])|(15[^4,\\D])|(14[57])|(17[0])|(17[7])|(18[0,0-9]))\\d{8}$";

    /**
     * 验证器的初始化工作
     * @param constraintAnnotation
     */
    @Override
    public void initialize(PhoneNo constraintAnnotation) {
        log.info("进入手机号码验证器....");
    }

    /**
     * 验证的具体逻辑
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return Pattern.matches(REG_PHONE, (String)value);
    }
}
