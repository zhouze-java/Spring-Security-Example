package com.security.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周泽
 * @date Create in 17:50 2019/8/8
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Object me(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
}
