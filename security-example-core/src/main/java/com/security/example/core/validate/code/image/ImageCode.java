package com.security.example.core.validate.code.image;

import com.security.example.core.validate.code.base.BaseCode;
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
public class ImageCode extends BaseCode {

    /**
     * 图片对象
     */
    private transient BufferedImage image;

    /**
     * 构造
     * @param image 图片对象
     * @param code 验证码
     * @param expireIn 过期秒数
     */
    public ImageCode (BufferedImage image, String code, int expireIn){
        super(code, LocalDateTime.now().plusSeconds(expireIn));
        this.image = image;
    }
}
