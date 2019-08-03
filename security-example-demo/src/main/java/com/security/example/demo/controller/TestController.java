package com.security.example.demo.controller;

import com.security.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 周泽
 * @date Create in 18:46 2019/8/1
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/list")
    public List<User> list(){
        List<User> users = new ArrayList<>();

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }

    @GetMapping("{id:\\d+}")
    public User getInfo(@PathVariable Long id){
        log.info("查询id是[{}]的数据.....", id);
        return new User();
    }

    @PostMapping
    public User insert(@Valid @RequestBody User user){
        log.info("添加接口,新建数据....");
        user.setId(RandomUtils.nextLong());
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        log.info("更新接口,更新id是[{}]的数据...", id);
        user.setId(id);

        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        log.info("删除id是:[{}]的数据...", id);
    }
}
