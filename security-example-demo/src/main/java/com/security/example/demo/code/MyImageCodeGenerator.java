package com.security.example.demo.code;

import com.security.example.core.validate.code.ValidateCodeGenerator;
import com.security.example.core.validate.code.image.ImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周泽
 * @date Create in 14:31 2019/8/11
 * @Description 自定义实现图片验证码生成.(打开Component注解,然后generate方法中加入逻辑即可)
 */
@Slf4j
//@Component("imageCodeGenerator")
public class MyImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(HttpServletRequest request) {
        log.info("这里实现应用层的验证码生成....");
        // 此处省略
        return null;
    }
}
