package com.security.example.demo.controller;

import com.security.example.app.authentication.social.signup.AppSignUpUtil;
import com.security.example.core.config.SecurityProperties;
import com.security.example.demo.exception.UserNotExistException;
import com.security.example.demo.model.User;
import com.security.example.demo.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author 周泽
 * @date Create in 17:50 2019/8/8
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String SUBMIT_REGISTER = "注册";

    private static final String SUBMIT_BINDING = "绑定";

    /**
     * 请求头中 token的前缀
     */
    private static final String AUTHORIZATION_PREFIX = "bearer ";

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppSignUpUtil appSignUpUtil;

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/me")
    public Object me(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        // 从请求头中获取到token
        String jwtToken = StringUtils.substringAfter(request.getHeader("Authorization"), AUTHORIZATION_PREFIX);
        log.info("请求头中的token:{}", jwtToken);

        // 获取配置中的 jwtTokenSignKey
        String jwtTokenSignKey = securityProperties.getOAuth2().getJwtTokenSignKey();
        Claims claims = Jwts.parser().setSigningKey(jwtTokenSignKey.getBytes("UTF-8")).parseClaimsJws(jwtToken).getBody();

        return claims;
    }

    @PostMapping("/register")
    public void register(User user, String type,HttpServletRequest request){
        Long userId;
        // 判断是注册还是绑定
        if (type.equals(SUBMIT_REGISTER)){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.insert(user);
            userId = user.getId();
        } else {
            // 首先需要验证用户名和密码,然后
            User dbUser = userService.findUserByPhoneNo(user.getPhoneNo());
            if (user == null) {
                throw new UserNotExistException();
            }
            if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                throw new RuntimeException("密码错误");
            }
            if (dbUser.getLocked()) {
                throw new RuntimeException("用户已被锁定");
            }
            if (!dbUser.getEnable()) {
                throw new RuntimeException("用户未启用");
            }

            userId = dbUser.getId();
        }

        // 最终要拿到业务系统中的 用户的唯一标识
//        providerSignInUtils.doPostSignUp(String.valueOf(userId), new ServletWebRequest(request));

        appSignUpUtil.doPostSignUp(request,String.valueOf(userId));
    }
}
