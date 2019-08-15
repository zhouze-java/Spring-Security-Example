package com.security.example.core.validate.code;

import com.security.example.core.validate.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 周泽
 * @date Create in 11:25 2019/8/14
 * @Description 通过请求参数拿到对应的处理器
 */
@Component
public class ValidateCodeProcessorHolder {

    /**
     * 收集系统中的所有{@link ValidateCodeProcessor} 的实现
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor (ValidateCodeType type) {
        return findValidateCodeProcessor(type.name().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();

        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}
