package com.security.example.core.authentication.social.qq.connect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author 周泽
 * @date Create in 22:08 2019/8/19
 * @Description 自定义QQ的 OAuth2Template
 */
@Slf4j
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        // 设置 useParametersForClientAuthentication 为true ,请求参数里面才会带上 client_id 和 client_secret 这两个参数
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        // 拿到父类创建的结果
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

        log.info("获取accessToken的响应:{}", responseStr);

        // 返回格式是 access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        // 拆分组装成 AccessGrant
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");

        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");

        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }
}
