package com.security.example.core.authentication.social.config;

import com.security.example.core.authentication.social.qq.connect.QQConnectionFactory;
import com.security.example.core.config.QQProperties;
import com.security.example.core.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author 周泽
 * @date Create in 11:11 2019/8/17
 * @Description
 */
@Configuration
@ConditionalOnProperty(prefix = "core.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }
}
