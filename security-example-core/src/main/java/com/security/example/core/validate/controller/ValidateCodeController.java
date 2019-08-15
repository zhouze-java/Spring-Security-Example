package com.security.example.core.validate.controller;

import com.security.example.core.config.SecurityConstants;
import com.security.example.core.validate.code.ValidateCodeProcessor;
import com.security.example.core.validate.code.ValidateCodeProcessorHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 周泽
 * @date Create in 18:05 2019/8/10
 * @Description 获取验证码
 */
@RestController
@Slf4j
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void getCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException, ServletRequestBindingException {
        ValidateCodeProcessor processor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
        processor.createCode(request, response);
    }

}
