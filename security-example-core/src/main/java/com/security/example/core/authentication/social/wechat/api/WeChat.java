package com.security.example.core.authentication.social.wechat.api;

import com.security.example.core.authentication.social.wechat.model.WeixinUserInfo;

/**
 * @author 周泽
 * @date Create in 9:56 2019/8/21
 * @Description 微信获取用户信息接口
 */
public interface WeChat {

    /**
     * 获取微信的用户信息
     * @return {@link WeixinUserInfo}
     */
    WeixinUserInfo getUserInfo(String openId);
}
