package com.security.example.core.validate.code;

import com.security.example.core.validate.code.image.ImageCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周泽
 * @date Create in 14:02 2019/8/11
 * @Description 生成验证码的接口
 */
public interface ValidateCodeGenerator {

    /**
     * 生成图形验证码
     * @param request
     * @return
     */
    ImageCode generate(HttpServletRequest request);

}
