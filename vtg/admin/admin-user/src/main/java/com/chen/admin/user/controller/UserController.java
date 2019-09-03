package com.chen.admin.user.controller;

import com.chen.admin.user.base.controller.BaseController;
import com.chen.admin.user.entity.UserEntity;
import com.chen.admin.user.entity.vo.PageVo;
import com.chen.admin.user.entity.vo.UserVo;
import com.chen.admin.user.service.ArticleService;
import com.chen.admin.user.service.ImageService;
import com.chen.admin.user.service.UserService;
import com.chen.admin.user.utils.Result;
import com.chen.admin.user.utils.SecurityUtil;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private ImageService imageService;

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
        String OldAvatar = user.getAvatar();
        userService.updateUserAvatar(user.getId(), headImg, OldAvatar);
        return Result.ok(headImg);
    }

    @ApiOperation(value = "上传头像", notes = "上传头像图片源文件到服务器")
    @PostMapping("/avatar/upload")
    public Result<String> uploadUserAvatar(@RequestParam("picture") MultipartFile picture) {
        //String pictureUrl = imageService.saveImg(picture);
        String pictureUrl = imageService.uploadImgWithAliyunOSS(picture);
        return Result.ok(pictureUrl);
    }

    @ApiOperation(value = "查询用户", notes = "查询所有用户")
    @GetMapping("/list")
    public PageInfo<UserEntity> selectList(PageVo page) {
        PageInfo<UserEntity> userLists = userService.selectList(page);
        return userLists;
    }

    @ApiOperation(value = "更新用户类型", notes = "更新用户类型")
    @PatchMapping("/updateUserType")
    public Result<Integer> updateUserType(@RequestParam("userId") Integer userId,
                                          @RequestParam("userType") Integer userType) {
        userService.updateUserType(userId, userType);
        return Result.ok(userType);
    }

    @ApiOperation(value = "搜索用户", notes = "条件查询用户")
    @GetMapping("/search")
    public PageInfo<UserEntity> searchArticle(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize,
                                             @RequestParam("userName")String userName) {
        PageVo page = new PageVo();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        UserEntity userEntity = new UserEntity();
        userEntity.setAccountName(userName);
        PageInfo<UserEntity> userLists = userService.getUserListSelective(page, userEntity);
        return userLists;
    }
}
