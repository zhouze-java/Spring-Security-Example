package com.security.example.core.validate.code.sms;

import com.security.example.core.config.SecurityProperties;
import com.security.example.core.validate.code.ValidateCodeGenerator;
import com.security.example.core.validate.code.base.BaseCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周泽
 * @date Create in 22:20 2019/8/12
 * @Description 短信验证码生成器
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public BaseCode generate(HttpServletRequest request) {
        String code  = RandomStringUtils.randomNumeric(securityProperties.getCode().getSmsCode().getCodeCount());
        return new SmsCode(code, securityProperties.getCode().getSmsCode().getExpireIn());
    }

}
