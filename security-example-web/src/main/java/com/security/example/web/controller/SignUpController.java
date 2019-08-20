package com.security.example.web.controller;

import com.security.example.core.config.SecurityConstants;
import com.security.example.core.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 周泽
 * @date Create in 14:31 2019/8/20
 * @Description 注册跳转
 */
@RestController
@RequestMapping(SecurityConstants.DEFAULT_SIGN_UP_URL)
public class SignUpController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping(produces = "text/html")
    public void signUpHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getSignUpPage());
    }

    @RequestMapping
    @ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
    public Map<String, Object> signUpJson() {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("message", "用户未注册,请引导用户到注册页面....");
        return resultMap;
    }
}
