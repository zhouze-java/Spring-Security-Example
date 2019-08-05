package com.security.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * @author 周泽
 * @date Create in 10:21 2019/8/5
 * @Description 处理响应时间的切片
 */
@Aspect
@Component
@Slf4j
public class TimerAspect {

    @Around("execution(* com.security.example.demo.controller.TestController.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 拿到请求的所有参数
        Object[] params = pjp.getArgs();

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 执行controller中具体的业务逻辑
        Object object = pjp.proceed();

        // 方法执行完成打印结束时间
        log.info("方法执行结束,耗时:{}ms", System.currentTimeMillis() - startTime);

        // 返回的是具体controller的返回值
        return object;
    }

}
