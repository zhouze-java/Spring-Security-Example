package com.security.example.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 周泽
 * @date Create in 22:41 2019/8/2
 * @Description 用户实体
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private Long id;

    private String name;

    private String password;

    private Integer sex;

    private String phoneNo;

    private Boolean locked;

    private Boolean enable;

    public User(Long id, String username, String password, Boolean locked, Boolean enable) {
        this.id = id;
        this.name = username;
        this.password = password;
        this.locked = locked;
        this.enable = enable;
    }
}
