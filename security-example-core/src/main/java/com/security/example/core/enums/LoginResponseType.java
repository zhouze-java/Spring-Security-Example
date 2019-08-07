package com.security.example.core.enums;

import java.io.Serializable;

/**
 * @author 周泽
 * @date Create in 23:03 2019/8/7
 * @Description 登录跳转类型
 */
public enum LoginResponseType implements Serializable {

    /**
     * 跳转请求
     */
    REDIRECT,

    /**
     * json返回
     */
    JSON,

    ;

}
