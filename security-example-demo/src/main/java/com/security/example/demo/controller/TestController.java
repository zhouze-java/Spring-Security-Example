package com.security.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周泽
 * @date Create in 18:46 2019/8/1
 * @Description
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
