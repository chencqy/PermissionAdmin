package com.chen.vtg.controller;

import com.chen.vtg.entity.UserEntity;
import com.chen.vtg.service.UserService;
import com.chen.vtg.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Chen
 * @Date : Create in 2018/8/21 20:49
 * @Description :
 * @Modify by :
 */

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<UserEntity> register(@RequestBody UserEntity userEntity) {
        if (userEntity == null) {
            logger.info("userEntity is null");
        }
        userService.insertIntoUser(userEntity);
        return Result.ok(userEntity);
    }

    @GetMapping("/login")
    public Result<Object> login() {
        return Result.ok(null);
    }
//    @GetMapping("/testException")
//    public String testException(@RequestParam("i") int i) {
//        if (i == 1) {
//            throw new UserNameNotMatchPasswordException("用户名与密码不匹配");
//        }
//
//        return "success";
//    }

}
