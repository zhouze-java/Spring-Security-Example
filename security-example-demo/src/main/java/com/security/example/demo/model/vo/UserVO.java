/**
 *
 * Copyright 2017 ishangban.cn, Inc. All rights reserved.
 */
package com.security.example.demo.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The table 
 */
@ApiModel(description = "用户VO")
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(min=0, max=11, message = "error.User.phoneNo.Length")
    @NotEmpty(message="error.User.phoneNo.NotEmpty")
    @ApiModelProperty(value="手机号,最大长度11", required=true, example="")
    private String phoneNo;
    
    @Length(min=0, max=100, message = "error.User.password.Length")
    @NotEmpty(message="error.User.password.NotEmpty")
    @ApiModelProperty(value="密码,最大长度100", required=true, example="")
//    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @Length(min=0, max=20, message = "error.User.username.Length")
    @NotEmpty(message="error.User.username.NotEmpty")
    @ApiModelProperty(value="用户名,最大长度20", required=true, example="")
    private String username;

}
