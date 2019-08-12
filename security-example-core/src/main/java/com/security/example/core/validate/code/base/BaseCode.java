package com.security.example.core.validate.code.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 周泽
 * @date Create in 21:13 2019/8/12
 * @Description
 */
@Data
@AllArgsConstructor
public class BaseCode implements Serializable {
    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 验证码是否过期
     * @return
     */
    public boolean expired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

}
