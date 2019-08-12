package com.security.example.core.validate.code.sms;

/**
 * @author 周泽
 * @date Create in 21:47 2019/8/12
 * @Description 发送短信验证码接口
 */
public interface SmsCodeSender {

    /**
     * 发送短信的接口
     * @param phoneNo
     * @param code
     */
    void send(String phoneNo, String code);
}
