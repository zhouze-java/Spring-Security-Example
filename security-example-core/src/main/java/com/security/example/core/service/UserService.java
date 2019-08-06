package com.security.example.core.service;

import com.security.example.core.model.User;

/**
 * @author 周泽
 * @date Create in 10:54 2019/8/6
 * @Description
 */
public interface UserService {

    /**
     * 根据用户名称去查询手机号
     * @param username
     * @return
     */
    User findUserByName(String username);
}
