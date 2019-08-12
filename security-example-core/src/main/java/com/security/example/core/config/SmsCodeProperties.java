package com.security.example.core.config;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 22:32 2019/8/12
 * @Description 短信验证码的配置类
 */
@Data
public class SmsCodeProperties extends BaseCodeProperties{

    public SmsCodeProperties(){
        setCodeCount(6);
    }
}
