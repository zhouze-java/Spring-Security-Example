package com.security.example.core.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author 周泽
 * @date Create in 11:08 2019/8/17
 * @Description QQ的相关配置
 */
@Data
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

}
