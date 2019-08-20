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
     * 跳转注册的路径
     */
    private String signUpUrl = "/signUp";

    /**
     * 跳转注册页
     */
    private String signUpPage = "/default-signUp.html";

    /**
     * 登录返回类型
     */
    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    /**
     * 记住我的秒数
     */
    private int rememberMeSeconds = 60 * 60 * 24;
}
