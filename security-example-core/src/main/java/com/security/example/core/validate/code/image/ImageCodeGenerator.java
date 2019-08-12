package com.security.example.core.validate.code.image;

import com.security.example.core.config.SecurityProperties;
import com.security.example.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周泽
 * @date Create in 14:09 2019/8/11
 * @Description 生成图片验证码的抽象类
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(HttpServletRequest request) {
        // 长宽先从请求中取
        int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getCode().getImageCode().getWidth());
        int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getCode().getImageCode().getHeight());

        // 过期时间和长度不能通过请求指定
        int codeCount = securityProperties.getCode().getImageCode().getCodeCount();
        int expire = securityProperties.getCode().getImageCode().getExpireIn();

        ValidateCode code = new ValidateCode(width, height, codeCount);
        return new ImageCode(code.getBuffImg(), code.getCode(), expire);
    }

}
