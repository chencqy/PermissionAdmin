package com.chen.vtg.controller;

import com.chen.vtg.entity.vo.UserVo;
import com.chen.vtg.service.UserService;
import com.chen.vtg.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/24 20:22
 * @Description :
 * @Modify by :
 */

@Api("用户接口")
@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "通过用户名称获取用户信息")
    @GetMapping("/user/info")
    public Result userInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVo userVo = userService.getUserInfo(userDetails.getUsername());
        return Result.ok(userVo);
    }
}
