package com.security.example.core.authentication.social.qq.connect;

import com.security.example.core.authentication.social.qq.api.QQ;
import com.security.example.core.authentication.social.qq.api.QQAdapter;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author 周泽
 * @date Create in 10:45 2019/8/17
 * @Description qq连接工厂
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {


    /**
     * 连接工厂构造
     * @param providerId 服务商的唯一id
     * @param appId
     * @param appSecret
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}
