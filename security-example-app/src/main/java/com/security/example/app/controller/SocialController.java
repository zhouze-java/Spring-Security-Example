package com.security.example.app.controller;

import com.security.example.app.authentication.social.signup.AppSignUpUtil;
import com.security.example.core.config.SecurityConstants;
import com.security.example.core.model.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周泽
 * @date Create in 22:28 2019/8/28
 * @Description
 */
@RestController
public class SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtil appSignUpUtil;

    @GetMapping(SecurityConstants.DEFAULT_APP_SIGNUP_URL)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){

        SocialUserInfo userInfo = new SocialUserInfo();

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));

        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());

        // 存到redis中去
        appSignUpUtil.saveConnectionData(request, connection.createData());

        return userInfo;
    }


}
