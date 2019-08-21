package com.security.example.core.authentication.social.wechat.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author 周泽
 * @date Create in 10:18 2019/8/21
 * @Description
 */
@Slf4j
public class WeChatOAuth2Template extends OAuth2Template {


    private String clientId;

    private String clientSecret;

    private String accessTokenUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 刷新token的url
     */
    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    public WeChatOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }

    /**
     * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
     */
    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url = url + "&appid=" + clientId + "&scope=snsapi_login";
        return url;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }

    /**
     * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }


    /**
     * 获取accesstoken
     * @param authorizationCode
     * @param redirectUri
     * @param additionalParameters
     * @return
     */
    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        // 拼接url
        StringBuilder accessTokenRequestUrl = new StringBuilder();
        accessTokenRequestUrl.append("?appid=").append(clientId);
        accessTokenRequestUrl.append("&secret=").append(clientSecret);
        accessTokenRequestUrl.append("&code=").append(authorizationCode);
        accessTokenRequestUrl.append("&grant_type=authorization_code");
        accessTokenRequestUrl.append("&redirect_uri=").append(redirectUri);

        return getAccessToken(accessTokenRequestUrl);
    }

    /**
     * 刷新token的方法
     * @param refreshToken
     * @param additionalParameters
     * @return
     */
    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {

        StringBuilder refreshTokenUrl = new StringBuilder(REFRESH_TOKEN_URL);

        refreshTokenUrl.append("?appid="+clientId);
        refreshTokenUrl.append("&grant_type=refresh_token");
        refreshTokenUrl.append("&refresh_token="+refreshToken);

        return getAccessToken(refreshTokenUrl);
    }

    private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl) {
        log.info("获取微信token,请求:{}", accessTokenRequestUrl);

        String responseStr = getRestTemplate().getForObject(accessTokenUrl.toString(), String.class);

        log.info("获取微信token,返回:{}", responseStr);

        // 转成一个map
        Map<String, Object> result = null;

        try {
            result = objectMapper.readValue(responseStr, Map.class);
        } catch (IOException e) {
            log.error("获取微信token,转换失败:{}", e);
        }

        // 返回错误码时直接返回空
        if(StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))){
            String errcode = MapUtils.getString(result, "errcode");
            String errmsg = MapUtils.getString(result, "errmsg");
            throw new RuntimeException("获取access token失败, errcode:" + errcode + ", errmsg:" + errmsg);
        }

        // 构建 AccessGrant 对象返回
        WeChatAccessGrant weChatAccessGrant = new WeChatAccessGrant(
                MapUtils.getString(result, "access_token"),
                MapUtils.getString(result, "scope"),
                MapUtils.getString(result, "refresh_token"),
                MapUtils.getLong(result, "expires_in")
        );
        weChatAccessGrant.setOpenId(MapUtils.getString(result, "openid"));

        return weChatAccessGrant;
    }
}
