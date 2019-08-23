package com.security.example.web.handler;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 周泽
 * @date Create in 21:59 2019/8/23
 * @Desciption 退出登录成功后的处理逻辑
 */
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private String logOutSuccessUrl;

    public MyLogoutSuccessHandler(String logOutSuccessUrl) {
        this.logOutSuccessUrl = logOutSuccessUrl;
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("注销成功.....");

        // 判断用户有没有配置注销成功页, 有的话做跳转,没有的话返回json提示
        if (StringUtils.isBlank(logOutSuccessUrl)) {
            Map<String, Object> map = new HashMap<>(1);
            map.put("message", "注销成功");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(map));
        } else {
            response.sendRedirect(logOutSuccessUrl);
        }
    }

}
