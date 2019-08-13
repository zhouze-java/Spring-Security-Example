package com.security.example.core.validate.controller;

import com.security.example.core.validate.code.ValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 周泽
 * @date Create in 18:05 2019/8/10
 * @Description 获取验证码
 */
@RestController
@Slf4j
public class ValidateCodeController {

    /**
     * 收集系统中的所有 {@link ValidateCodeProcessor} 的实现
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @GetMapping("code/{type}")
    public void getCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException, ServletRequestBindingException {
        ValidateCodeProcessor processor = validateCodeProcessors.get(type + "CodeProcessor");
        processor.createCode(request, response);
    }

}
