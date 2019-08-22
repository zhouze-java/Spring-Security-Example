package com.security.example.core.authentication.social.config;

import com.security.example.core.authentication.social.vo.ConnectView;
import com.security.example.core.authentication.social.wechat.connect.WeChatConnectionFactory;
import com.security.example.core.config.SecurityProperties;
import com.security.example.core.config.WeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @author 周泽
 * @date Create in 17:08 2019/8/21
 * @Description
 */
@Configuration
@ConditionalOnProperty(prefix = "core.security.social.weChat", name = "app-id")
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeChatProperties weChat = securityProperties.getSocial().getWeChat();
        return new WeChatConnectionFactory(weChat.getProviderId(), weChat.getAppId(), weChat.getAppSecret());
    }

    @Bean({"connect/weChatConnected","connect/weChatConnect"})
    @ConditionalOnMissingBean(name = "weChatConnectView" )
    public View weChatConnectView(){
        return new ConnectView();
    }
}
