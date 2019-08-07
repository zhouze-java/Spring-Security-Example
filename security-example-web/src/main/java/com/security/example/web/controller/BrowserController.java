package com.security.example.web.controller;

import com.security.example.core.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
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
 * @date Create in 17:52 2019/8/6
 * @Description 请求来源判断controller
 */
@Slf4j
@RestController
@RequestMapping("/authentication/require")
public class BrowserController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 需要身份认证的时候先跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces = "text/html")
    public void requireAuthenticationHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
        }
    }

    @RequestMapping
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> requireAuthenticationJson() {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("message", "认证失败,请登录...");
        return resultMap;
    }

}
