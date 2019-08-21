package com.security.example.core.authentication.social.wechat.connect;

import lombok.Data;
import org.springframework.social.oauth2.AccessGrant;

/**
 * @author 周泽
 * @date Create in 14:07 2019/8/21
 * @Description 微信的access_token等信息
 * 与标准OAuth2协议不同，微信在获取access_token时会同时返回openId,并没有单独的通过accessToke换取openId的服务,所以在这里继承了标准AccessGrant，添加了openId字段，作为对微信access_token信息的封装。
 */
@Data
public class WeChatAccessGrant extends AccessGrant {

    private String openId;

    public WeChatAccessGrant(){
        super("");
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

}
