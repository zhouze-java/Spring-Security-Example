package com.security.example.core.authentication.social.wechat.api;

import com.security.example.core.authentication.social.wechat.model.WeixinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author 周泽
 * @date Create in 14:44 2019/8/21
 * @Description 微信登录适配器
 */
public class WeChatAdapter implements ApiAdapter<WeChat> {

    private String openId;

    public WeChatAdapter() {}

    public WeChatAdapter(String openId){
        this.openId = openId;
    }

    @Override
    public boolean test(WeChat api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeChat api, ConnectionValues values) {
        WeixinUserInfo userInfo = api.getUserInfo(openId);

        values.setProviderUserId(userInfo.getOpenid());
        values.setImageUrl(userInfo.getHeadimgurl() != null ? userInfo.getHeadimgurl() : null);
        values.setProfileUrl(null);
        values.setDisplayName(userInfo.getNickname());
    }

    @Override
    public UserProfile fetchUserProfile(WeChat api) {
        return null;
    }

    @Override
    public void updateStatus(WeChat api, String message) {

    }
}
