package com.security.example.core.validate.filter;

import com.security.example.core.config.SecurityConstants;
import com.security.example.core.config.SecurityProperties;
import com.security.example.core.validate.code.ValidateCodeProcessorHolder;
import com.security.example.core.validate.code.ValidateCodeType;
import com.security.example.core.validate.exception.ValidateCodeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 周泽
 * @date Create in 23:33 2019/8/10
 * @Description 验证码过滤器
 */
@Data
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 用来匹配url
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        // 首先,登录请求必须验证.直接放进去
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        // 不同的表单做不同的处理
        addUrlToMap(securityProperties.getCode().getImageCode().getUrls(), ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getSmsCode().getUrls(), ValidateCodeType.SMS);
    }

    /**
     * 从配置中拆出路径来放到url里面
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isBlank(urlString)) {
            return;
        }

        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
        for (String url : urls) {
            urlMap.put(url, type);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("进入验证码校验的filter.....");
        try {
            // 这里做验证
            validate(request);
        } catch (ValidateCodeException exception) {
            // 验证失败调用验证失败的处理
            authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
            // 直接返回 不走下一个过滤器了
            return;
        }

        doFilter(request, response, filterChain);
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException {

        ValidateCodeType type = getValidateCodeType(request);
        if (type == null){
            return;
        }
        log.info("校验请求[{}]中的验证码,验证码类型是[{}]", request.getRequestURI(), type);

        validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(request);

    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            return null;
        }

        Set<String> urls = urlMap.keySet();
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                result = urlMap.get(url);
            }
        }

        return result;
    }

}
