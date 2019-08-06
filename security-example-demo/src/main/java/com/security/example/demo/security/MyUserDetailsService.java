package com.security.example.demo.security;

import com.security.example.core.model.User;
import com.security.example.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author 周泽
 * @date Create in 11:59 2019/8/6
 * @Description 自定义登录
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 这里是模拟从数据库查询用户
        User user = userService.findUserByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 查询出来,返回去
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getEnable(),
                true,
                true,
                !user.getLocked(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
