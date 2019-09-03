package com.chen.auth.authorization.controller;

import com.chen.auth.authorization.oauth.MyAuthenticationSuccessHandler;
import com.chen.auth.authorization.oauth.MyAuthenticationToken;
import com.chen.auth.authorization.oauth.github.MyAuthentication;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.PostMapping;
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
    private MyAuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private MyAuthentication gitHubAuthentication;

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
