package com.security.example.core.validate.code.image;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 周泽
 * @date Create in 17:56 2019/8/10
 * @Description 图形验证码实体类
 */
@Data
public class ImageCode implements Serializable {

    /**
     * 图片对象
     */
    private transient BufferedImage image;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 验证码是否过期
     * @return
     */
    public boolean expired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    /**
     * 构造
     * @param image 图片对象
     * @param code 验证码
     * @param expireIn 过期秒数
     */
    public ImageCode (BufferedImage image, String code, int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
}
