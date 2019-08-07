package com.security.example.core.service.impl;

import com.security.example.core.service.UserService;
import com.security.example.core.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 周泽
 * @date Create in 11:05 2019/8/6
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    private static Map<String, User> userMap = new HashMap<>(3);

    /**
     * 初始化数据,模拟从数据库获取数据
     */
    static{
        // 密码是经过加密的 都是 123456  通过 BCryptPasswordEncoder 加密
        User user1 = new User(1L, "zhangsan", "$2a$10$W17jDdV96PUBlVx/PL6zDuaK3rv9uH.DKLwkFaD6lhOPkv3GD5sEu", false, true);
        User user2 = new User(2L, "lisi", "$2a$10$W17jDdV96PUBlVx/PL6zDuaK3rv9uH.DKLwkFaD6lhOPkv3GD5sEu", false, true);
        User user3 = new User(3L, "lockedtest", "$2a$10$W17jDdV96PUBlVx/PL6zDuaK3rv9uH.DKLwkFaD6lhOPkv3GD5sEu", true, true);
        User user4 = new User(4L, "enabletest", "$2a$10$W17jDdV96PUBlVx/PL6zDuaK3rv9uH.DKLwkFaD6lhOPkv3GD5sEu", false, false);

        userMap.put(user1.getName(), user1);
        userMap.put(user2.getName(), user2);
        userMap.put(user3.getName(), user3);
        userMap.put(user4.getName(), user4);

    }

    @Override
    public User findUserByName(String username) {
        User user = userMap.get(username);
        return user;
    }

}