package com.chen.vtg.controller;

import com.chen.vtg.entity.UserEntity;
import com.chen.vtg.service.UserService;
import com.chen.vtg.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Chen
 * @Date : Create in 2018/8/21 20:49
 * @Description :
 * @Modify by :
 */

@Api("登录接口")
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "新用户注册")
    @PostMapping("/register")
    public Result<UserEntity> register(@RequestBody UserEntity userEntity) {
        if (userEntity == null) {
            logger.info("userEntity is null");
        }
        userService.insertIntoUser(userEntity);
        return Result.ok(userEntity);
    }

    // TODO : 暂时弃用
    @GetMapping("/login")
    public Result<Object> login() {
        return Result.ok(null);
    }

}
