package com.security.example.core.validate.code.sms;

import com.security.example.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 周泽
 * @date Create in 14:39 2019/8/13
 * @Description 短信验证码发送
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(HttpServletRequest request, HttpServletResponse response, SmsCode validateCode) throws IOException, ServletRequestBindingException {
        String phoneNo = ServletRequestUtils.getRequiredStringParameter(request, "phoneNo");
        smsCodeSender.send(phoneNo, validateCode.getCode());
    }
}
