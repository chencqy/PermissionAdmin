package com.chen.vtg.controller;

import com.chen.vtg.base.controller.BaseController;
import com.chen.vtg.entity.UserEntity;
import com.chen.vtg.entity.vo.UserVo;
import com.chen.vtg.service.ArticleService;
import com.chen.vtg.service.UserService;
import com.chen.vtg.utils.Result;
import com.chen.vtg.utils.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/24 20:22
 * @Description :
 * @Modify by :
 */

@Api("用户接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserEntity, Integer, UserService> {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "获取用户信息", notes = "通过用户名称获取用户信息")
    @GetMapping("/info")
    public Result<UserVo> userInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVo userVo = userService.getUserInfo(userDetails.getUsername());
        return Result.ok(userVo);
    }

    @ApiOperation(value = "更新头像", notes = "更新头像url")
    @PatchMapping("/updateAvatar")
    public Result<String> updateAvatar(@RequestParam("headImg") String headImg) {
        String accountName = SecurityUtil.getLoginName();
        UserVo user = userService.getUserInfo(accountName);
        userService.updateUserAvatar(user.getId(), headImg);
        return Result.ok(headImg);
    }

    @ApiOperation(value = "上传头像", notes = "上传头像图片源文件到服务器")
    @PostMapping("/avatar/upload")
    public Result<String> uploadUserAvatar(@RequestParam("picture") MultipartFile picture) {
        String pictureUrl = articleService.saveImage(picture);
        String accountName = SecurityUtil.getLoginName();
        UserVo user = userService.getUserInfo(accountName);
        userService.updateUserAvatar(user.getId(), pictureUrl);
        return Result.ok(pictureUrl);
    }
}
