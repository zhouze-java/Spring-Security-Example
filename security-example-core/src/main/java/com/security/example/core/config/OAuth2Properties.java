package com.security.example.core.config;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 10:12 2019/8/30
 * @Description OAuth2d 配置类
 */
@Data
public class OAuth2Properties  {

    /**
     * JWT的签名key
     */
    private String jwtTokenSignKey = "default-token-sign-key";

}
