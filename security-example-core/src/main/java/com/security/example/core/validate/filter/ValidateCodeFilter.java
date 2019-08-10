package com.security.example.core.validate.filter;

import com.security.example.core.config.redis.Constants;
import com.security.example.core.validate.code.image.ImageCode;
import com.security.example.core.validate.exception.ValidateCodeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 周泽
 * @date Create in 23:33 2019/8/10
 * @Description 验证码过滤器
 */
@Data
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/authentication/from", request.getRequestURI())
                &&
                StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {

            try {
                // 这里做验证
                validate(request);
            } catch (ValidateCodeException exception) {
                // 验证失败调用验证失败的处理
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                // 直接返回 不走下一个过滤器了
                return;
            }

        }
        doFilter(request, response, filterChain);
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        // 缓存中拿出验证码来
        String redisKey = Constants.IMAGE_CODE_KEY_PREFIX + request.getSession().getId();
        log.info("从缓存里取验证码,redisKey:{}", redisKey);
        ImageCode code = (ImageCode) redisTemplate.opsForValue().get(redisKey);

        // 参数中拿出验证码来,作比较
        String codeInRequest = ServletRequestUtils.getStringParameter(request, "imageCode");
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (code == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (code.expired()) {
            redisTemplate.delete(Constants.IMAGE_CODE_KEY_PREFIX + request.getSession().getId());
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equalsIgnoreCase(code.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码错误");
        }

        // 最后通过的话也从缓存清除掉
        redisTemplate.delete(Constants.IMAGE_CODE_KEY_PREFIX + request.getSession().getId());
    }
}
