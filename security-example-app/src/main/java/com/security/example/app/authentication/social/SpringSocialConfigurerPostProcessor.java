package com.security.example.app.authentication.social;

import com.security.example.core.authentication.social.config.MySpringSocialConfigurer;
import com.security.example.core.config.SecurityConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 周泽
 * @date Create in 22:22 2019/8/28
 * @Description {@link com.security.example.core.authentication.social.config.MySpringSocialConfigurer} 的后处理器
 */
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor
{

    /**
     * 任何bean初始化之前做的事情
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 任何bean初始化之后做的事情
     * 我们这里要做的就是在 SpringSocialConfigurer 加载完了之后,把他的注册跳转的url改掉
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName,"securitySocialConfigurer")){
            MySpringSocialConfigurer mySpringSocialConfigurer = (MySpringSocialConfigurer) bean;
            mySpringSocialConfigurer.signupUrl(SecurityConstants.DEFAULT_APP_SIGNUP_URL);
            return mySpringSocialConfigurer;
        }
        return null;
    }
}
