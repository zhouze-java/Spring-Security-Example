package com.security.example.core.authentication.social.qq;

import java.io.IOException;

/**
 * @author 周泽
 * @date Create in 21:21 2019/8/16
 * @Description 获取QQ的用户信息接口
 */
public interface QQ {

    /**
     * 获取用户信息
     * @return {@link QQUserInfo}
     */
    QQUserInfo getUserInfo() throws IOException;
}
