package com.security.example.core.validate.code.sms;

import com.security.example.core.validate.code.base.BaseCode;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 周泽
 * @date Create in 21:16 2019/8/12
 * @Description 短信验证码model
 */
@Data
public class SmsCode extends BaseCode {

    /**
     * 父类构造
     * @param code 验证码
     * @param expireIn 过期秒数
     */
    public SmsCode(String code, int expireIn) {
        super(code, LocalDateTime.now().plusSeconds(expireIn));
    }

}
