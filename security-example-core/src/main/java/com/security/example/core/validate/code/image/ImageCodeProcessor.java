package com.security.example.core.validate.code.image;

import com.security.example.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 周泽
 * @date Create in 14:20 2019/8/13
 * @Description 图片验证码发送的实现
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(HttpServletRequest request, HttpServletResponse response, ImageCode imageCode) throws IOException {
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
