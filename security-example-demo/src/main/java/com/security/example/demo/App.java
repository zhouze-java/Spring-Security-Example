package com.security.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhouze
 * @desc ComponentScan 配置父级包, 否则无法扫描到其他工程里面的配置
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.security.example"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
