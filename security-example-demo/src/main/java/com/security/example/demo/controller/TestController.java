package com.security.example.demo.controller;

import com.security.example.demo.model.User;
import com.security.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 周泽
 * @date Create in 18:46 2019/8/1
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        List<User> users = userService.getAll(new User());
        return users;
    }

    @GetMapping("{id:\\d+}")
    public User getInfo(@PathVariable Long id){
        log.info("查询id是[{}]的数据.....", id);
        return userService.getUserById(id);
    }

    @GetMapping("pwd/{pwd}")
    public String passwordEncoder(@PathVariable String pwd){
        String enCodePwd = passwordEncoder.encode(pwd);
        log.info("加密后的PWD是.....", enCodePwd);
        return enCodePwd;
    }

    @GetMapping("match/{pwd}")
    public Boolean match(@PathVariable String pwd){
        Boolean flag = passwordEncoder.matches(pwd, "$2a$10$s3bbhyxx86tPj6FRMvMhaOcj3Vq18ux6ZbIuDLZsDyhNvpRGSbNa2");
        return flag;
    }

    @PostMapping
    public User insert(@Valid @RequestBody User user){
        log.info("添加接口,新建数据....");
        userService.insert(user);
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        log.info("更新接口,更新id是[{}]的数据...", id);
        userService.update(user);
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        log.info("删除id是:[{}]的数据...", id);
    }
}
