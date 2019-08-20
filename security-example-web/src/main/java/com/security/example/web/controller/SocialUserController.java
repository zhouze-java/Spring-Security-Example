package com.security.example.web.controller;

import com.security.example.core.config.SecurityConstants;
import com.security.example.web.model.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周泽
 * @date Create in 15:23 2019/8/20
 * @Description 获取 Social 获取到的用户信息
 */
@RestController
public class SocialUserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping(SecurityConstants.GET_SOCIAL_USER_URL)
    private SocialUserInfo getSocialUser(HttpServletRequest request){
        SocialUserInfo userInfo = new SocialUserInfo();

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));

        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());

        return userInfo;
    }
}
