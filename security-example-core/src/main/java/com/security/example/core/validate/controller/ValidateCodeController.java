package com.security.example.core.validate.controller;

import com.security.example.core.config.SecurityProperties;
import com.security.example.core.config.redis.Constants;
import com.security.example.core.validate.code.ValidateCodeGenerator;
import com.security.example.core.validate.code.image.ImageCode;
import com.security.example.core.validate.code.image.ValidateCode;
import com.security.example.core.validate.code.sms.SmsCode;
import com.security.example.core.validate.code.sms.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
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
    private RedisTemplate redisTemplate;

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @GetMapping("code/image")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 根据随机数生成数字
        ImageCode imageCode = (ImageCode)imageCodeGenerator.generate(request);

        // 将随机数存到缓存中
        String redisKey = Constants.IMAGE_CODE_KEY_PREFIX + request.getSession().getId();
        log.info("将验证码放到缓存中,redisKey:{}", request.getSession().getId());
        redisTemplate.opsForValue().set(redisKey, imageCode);

        // 将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping("code/sms")
    public void getSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        // 从请求中获取手机号
        String phoneNo = ServletRequestUtils.getRequiredStringParameter(request, "phoneNo");

        // 根据随机数生成数字
        SmsCode smsCode = (SmsCode) smsCodeGenerator.generate(request);

        // 将随机数存到缓存中
        String redisKey = Constants.LOGIN_SMS_CODE_KEY_PREFIX + phoneNo;
        log.info("将验证码放到缓存中,redisKey:{}", request.getSession().getId());
        redisTemplate.opsForValue().set(redisKey, smsCode);

        // 发送短信验证码
        smsCodeSender.send(phoneNo, smsCode.getCode());
    }
}
