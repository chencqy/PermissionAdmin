package com.chen.admin.user.controller;

import com.chen.admin.user.config.authentication.MyAuthentication;
import com.chen.admin.user.config.authentication.MyAuthenticationSuccessHandler;
import com.chen.admin.user.config.authentication.MyAuthenticationToken;
import com.chen.admin.user.entity.UserEntity;
import com.chen.admin.user.service.UserService;
import com.chen.admin.user.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Autowired
    private MyAuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private MyAuthentication gitHubAuthentication;

    @ApiOperation(value = "用户注册", notes = "新用户注册")
    @PostMapping("/register")
    public Result<UserEntity> register(@RequestBody UserEntity user) {
        if (user == null) {
            logger.info("userEntity is null");
        }
        userService.insertIntoUser(user);
        return Result.ok(user);
    }

    @PostMapping("/login/github")
    public OAuth2AccessToken loginForGitHub(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code)
            throws IOException {

        if (code == null) {
            code = "";
        }

        code = code.trim();

        String id = gitHubAuthentication.getUserId(code);

        MyAuthenticationToken authRequest = new MyAuthenticationToken(id);

        authRequest.setDetails(new OAuth2AuthenticationDetails(request));

        OAuth2AccessToken oAuth2AccessToken = loginSuccessHandler.getAccessToken(request, response, authRequest);

        return oAuth2AccessToken;
    }


}
