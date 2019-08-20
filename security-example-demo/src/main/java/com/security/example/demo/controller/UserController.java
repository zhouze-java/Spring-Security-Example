package com.security.example.demo.controller;

import com.security.example.core.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周泽
 * @date Create in 17:50 2019/8/8
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/me")
    public Object me(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @PostMapping("/register")
    public void register(User user, HttpServletRequest request){
        // ..省略和数据库的交互
        // 最终要拿到业务系统中的 用户的唯一标识
        providerSignInUtils.doPostSignUp(String.valueOf(1L), new ServletWebRequest(request));
    }
}
