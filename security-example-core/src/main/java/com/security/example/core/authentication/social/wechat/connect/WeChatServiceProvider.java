package com.security.example.core.authentication.social.wechat.connect;

import com.security.example.core.authentication.social.wechat.api.WeChat;
import com.security.example.core.authentication.social.wechat.api.WeChatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author 周泽
 * @date Create in 10:12 2019/8/21
 * @Description 微信 ServiceProvider
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeChatServiceProvider(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public WeChat getApi(String accessToken) {
        return new WeChatImpl(accessToken);
    }
}
