package com.security.example.core.authentication.social.qq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author 周泽
 * @date Create in 21:25 2019/8/16
 * @Description 获取用户信息的实现类
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    public static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        // 有了Appid 和 token之后去取openid
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        log.info("QQ获取用户openid结果:{}", result);
        // 返回格式是 callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} ) 字符串所以需要截取一下
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }

    @Override
    public QQUserInfo getUserInfo() throws IOException {
        // 有了openid之后就可以获取用户信息了
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("QQ获取用户信息结果:{}", result);

        // 转成对象返回
        return objectMapper.readValue(result, QQUserInfo.class);
    }
}
