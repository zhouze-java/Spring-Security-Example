package com.security.example.core.validate.code;

import org.springframework.web.bind.ServletRequestBindingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 周泽
 * @date Create in 10:43 2019/8/13
 * @Description 验证码处理器
 */
public interface ValidateCodeProcessor  {

    /**
     * 生成验证码,放到缓存,以及发送
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     */
    void createCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException;

}
