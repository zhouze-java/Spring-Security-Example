package com.security.example.core.validate.code.impl;

import com.security.example.core.config.redis.Constants;
import com.security.example.core.validate.code.ValidateCodeGenerator;
import com.security.example.core.validate.code.ValidateCodeProcessor;
import com.security.example.core.validate.code.ValidateCodeType;
import com.security.example.core.validate.code.base.BaseCode;
import com.security.example.core.validate.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 周泽
 * @date Create in 10:46 2019/8/13
 * @Description 验证码发送的抽象实现
 */
@Slf4j
public abstract class AbstractValidateCodeProcessor<C extends BaseCode> implements ValidateCodeProcessor {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 收集系统中的所有 {@link ValidateCodeGenerator} 的实现
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {
        // 生成
        C validateCode = generate(request);
        // 放缓存
        saveCache(request, validateCode);
        // 发送给用户
        send(request, response, validateCode);
    }

    /**
     * 生成验证码
     * @param request
     * @return
     */
    public C generate(HttpServletRequest request) {
        // 获取验证码类型
        String type = getValidateCodeType(request).name().toLowerCase();

        // 获取验证码生成器的名称
        String generatorName = type + "CodeGenerator";
        // 从 validateCodeGenerators 这个Map中取出
        ValidateCodeGenerator generator = validateCodeGenerators.get(generatorName);
        if (generator == null) {
            throw new ValidateCodeException("验证码生成器 " + generatorName + " 不存在");
        }

        return (C)generator.generate(request);
    }

    /**
     * 获取存入缓存的key
     * @param request
     * @return
     */
    private String getRedisKey(HttpServletRequest request) throws ServletRequestBindingException {
        StringBuilder redisKey = new StringBuilder();
        // 获取验证码类型
        ValidateCodeType validateCodeType = getValidateCodeType(request);

        redisKey.append(Constants.VALIDATE_CODE_KEY_PREFIX)
                .append(validateCodeType.name().toLowerCase())
                .append(":");
        // 判断是哪种类型的验证码
        if (validateCodeType.equals(ValidateCodeType.IMAGE)) {
            // 图片的
            redisKey.append(request.getSession().getId());
        } else if(validateCodeType.equals(ValidateCodeType.SMS)){
            // 短信的
            redisKey.append(ServletRequestUtils.getRequiredStringParameter(request, "phoneNo"));
        }
        return redisKey.toString();
    }

    /**
     * 保存到缓存里面
     * @param request
     * @param validateCode
     */
    private void saveCache(HttpServletRequest request, C validateCode) throws ServletRequestBindingException {
        // 将随机数存到缓存中
        String redisKey = getRedisKey(request);
        log.info("将验证码[{}]放到缓存中,redisKey:{}",validateCode.getCode(), redisKey);
        redisTemplate.opsForValue().set(redisKey, validateCode);
    }

    /**
     * 根据请求的url获取校验码的类型
     * @param request
     * @return ValidateCodeType
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 具体的发送验证码的方法, 交给子类去实现
     * @param request
     * @param response
     * @param validateCode
     * @throws IOException
     * @throws ServletRequestBindingException
     */
    protected abstract void send(HttpServletRequest request, HttpServletResponse response, C validateCode) throws IOException, ServletRequestBindingException;

    @Override
    public void validate(HttpServletRequest request) throws ServletRequestBindingException {
        // 获取验证的类型
        ValidateCodeType processorType = getValidateCodeType(request);
        // 获取redis中存的key的值
        String redisKey = getRedisKey(request);

        // 从缓存中拿出来
        C codeInCache = (C) redisTemplate.opsForValue().get(redisKey);

        // 然后是请求中的验证码
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request, processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInCache == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInCache.expired()) {
            redisTemplate.delete(redisKey);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equalsIgnoreCase(codeInCache.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码错误");
        }

        // 最后通过的话也从缓存清除掉
        redisTemplate.delete(redisKey);

    }
}
