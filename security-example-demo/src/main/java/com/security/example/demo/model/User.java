/**
 *
 * Copyright 2017 ishangban.cn, Inc. All rights reserved.
 */
package com.security.example.demo.model;

import com.security.example.demo.model.vo.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The table
 * @author zhouze
 */
@Data
@ApiModel(description = "用户")
@AllArgsConstructor
@NoArgsConstructor
public class User extends UserVO {

    private static final long serialVersionUID = 1L;
    

    @ApiModelProperty(value="ID", example="")
    private Long id;
    
    @ApiModelProperty(value="启用", example="")
    private Boolean enable;

    @ApiModelProperty(value="锁定", required=true, example="")
    private Boolean locked;

}
