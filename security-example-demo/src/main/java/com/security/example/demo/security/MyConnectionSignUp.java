package com.security.example.demo.security;


import com.security.example.demo.model.User;
import com.security.example.demo.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author 周泽
 * @date Create in 22:28 2019/8/20
 * @Description 第三方登录默认的注册逻辑
 */
@Component
public class MyConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserService userService;

    @Override
    public String execute(Connection<?> connection) {

        // 这里应该去根据自己的业务去创建一个用户,并返回用户的唯一标识
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPhoneNo(StringUtils.EMPTY);
        user.setPassword(StringUtils.EMPTY);
        userService.insert(user);
        return user.getId().toString();
    }
}
