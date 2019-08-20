package com.security.example.web.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 周泽
 * @date Create in 15:26 2019/8/20
 * @Description 第三方用户信息
 */
@Data
public class SocialUserInfo implements Serializable {
    /**
     * 服务提供商id
     */
    private String providerId;

    /**
     * 第三方的用户id
     */
    private String providerUserId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headimg;
}
