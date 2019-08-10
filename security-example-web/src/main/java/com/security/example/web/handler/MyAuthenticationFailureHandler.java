package com.security.example.web.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.example.core.config.SecurityProperties;
import com.security.example.core.enums.LoginResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 周泽
 * @date Create in 22:04 2019/8/7
 * @Description
 */
@Slf4j
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("认证失败...");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())) {
            // 只返回错误消息
            Map<String, Object> map = new HashMap<>(1);
            map.put("message", exception.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(map));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }

}
