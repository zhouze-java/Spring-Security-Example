package com.security.example.app.authentication.social.signup;

import com.security.example.app.exception.AppSerectException;
import com.security.example.core.config.redis.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author 周泽
 * @date Create in 22:05 2019/8/28
 * @Description 注册工具类
 */
@Component
public class AppSignUpUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * 将第三方用户信息放到redis缓存中去
     * @param request
     * @param connectionData
     */
    public void saveConnectionData(HttpServletRequest request, ConnectionData connectionData) {
        redisTemplate.opsForValue().set(getRedisKey(request), connectionData, 10, TimeUnit.MINUTES);
    }

    /**
     * 绑定业务系统中的用户
     * @param request
     * @param userId
     */
    public void doPostSignUp(HttpServletRequest request, String userId){

        // 判断缓存中有没有存第三方的用户信息
        String redisKey = getRedisKey(request);
        if (!redisTemplate.hasKey(redisKey)) {
            throw new AppSerectException("无法找到缓存中的用户社交账号信息");
        }

        // 从缓存中取出来
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(redisKey);

        // ConnectionData对象转成 connection对象
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId()).createConnection(connectionData);

        // 操作数据库 创建connection信息
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

    }

    /**
     * 获取缓存的key
     * @param request
     * @return
     */
    private String getRedisKey(HttpServletRequest request) {
        // 从请求中获取设备id
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new AppSerectException("deviceId不能为空");
        }

        return Constants.SOCIAL_USER_INFO_KEY_PREFIX + deviceId;
    }
}
