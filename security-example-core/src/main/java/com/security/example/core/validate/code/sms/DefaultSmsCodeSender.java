package com.security.example.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 周泽
 * @date Create in 21:50 2019/8/12
 * @Description
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String phoneNo, String code) {
        log.info("调用第三方短信接口,目标手机号:[{}], 验证码:[{}]", phoneNo, code);
    }

}
