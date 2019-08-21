package com.security.example.core.authentication.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.example.core.authentication.social.wechat.model.WeixinUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author 周泽
 * @date Create in 9:58 2019/8/21
 * @Description 微信登录实现类
 */
@Slf4j
public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {


    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    public WeChatImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    @Override
    public WeixinUserInfo getUserInfo(String openId) {
        String url = URL_GET_USER_INFO + openId;
        String responseStr = getRestTemplate().getForObject(url, String.class);

        log.info("获取微信信息的用户返回数据:{}", responseStr);

        if(StringUtils.contains(responseStr, "errcode")) {
            return null;
        }

        WeixinUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(responseStr, WeixinUserInfo.class);
        } catch (IOException e) {
            log.info("微信用户信息转换失败:{}", e);
        }

        return userInfo;
    }
}
