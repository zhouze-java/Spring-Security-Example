package com.security.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 周泽
 * @date Create in 10:20 2019/8/3
 * @Description 手机号验证注解
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNoValidator.class)
public @interface PhoneNo {

    String message() default "手机号格式有误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
