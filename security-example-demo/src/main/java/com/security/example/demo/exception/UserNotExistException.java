package com.security.example.demo.exception;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 14:35 2019/8/3
 * @Description 用户不存在异常
 */
@Data
public class UserNotExistException extends RuntimeException {

    private Long id;

    public UserNotExistException(Long id) {
        super("用户不存在");
        this.id = id;
    }

    public UserNotExistException() {
        super("用户不存在");
    }

}
