package com.security.example.demo.security;


import com.security.example.demo.model.User;
import com.security.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author 周泽
 * @date Create in 11:59 2019/8/6
 * @Description 自定义登录
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("进行用户认证...");
        User user = userService.findUserByPhoneNo(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 查询出来,返回去
        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
                user.getPassword(),
                user.getEnable(),
                true,
                true,
                !user.getLocked(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        // 根据id去查询用户信息
        User user = userService.getUserById(Long.valueOf(userId));

        return new SocialUser(userId ,
                user.getPassword(),
                user.getEnable(),
                true,
                true,
                !user.getLocked(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }
}
