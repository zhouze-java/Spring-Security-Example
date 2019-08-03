package com.security.example.demo.model;

import com.security.example.demo.validator.PhoneNo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 周泽
 * @date Create in 22:41 2019/8/2
 * @Description 用户实体
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private String password;

    private Integer sex;

    @PhoneNo
    private String phoneNo;
}
