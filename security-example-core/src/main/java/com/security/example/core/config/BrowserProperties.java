package com.security.example.core.config;

import com.security.example.core.enums.LoginResponseType;
import lombok.Data;

/**
 * @author 周泽
 * @date Create in 16:22 2019/8/7
 * @Description 浏览器配置
 */
@Data
public class BrowserProperties {

    /**
     * 登录页配置
     */
     private String loginPage = "/login.html";

    /**
     * 登录返回类型
     */
    private LoginResponseType loginType = LoginResponseType.JSON;
}
