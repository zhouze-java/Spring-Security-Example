package com.security.example.core.authentication.social.qq.api;

import com.security.example.core.authentication.social.qq.model.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author 周泽
 * @date Create in 10:36 2019/8/17
 * @Description QQ Api适配器
 */
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试当前的API是否可以访问
     * @param api
     * @return
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * 适配,把 ConnectionValues 需要的数据set进去
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo qqUserInfo = api.getUserInfo();
        values.setDisplayName(qqUserInfo.getNickname());
        values.setImageUrl(qqUserInfo.getFigureurl_qq_1());
        // 用户在服务商的唯一id
        values.setProviderUserId(qqUserInfo.getOpenId());
        // 用户主页, QQ没有
        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
    }
}
