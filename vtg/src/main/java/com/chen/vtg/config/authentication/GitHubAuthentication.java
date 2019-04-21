package com.chen.vtg.config.authentication;

import com.chen.vtg.entity.UserEntity;
import com.chen.vtg.exception.LoginFailureException;
import com.chen.vtg.mapper.UserEntityMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : Chen
 * @Date : Create in 2018/9/13 20:02
 * @Description :
 * @Modify by :
 */
@Service
public class GitHubAuthentication implements MyAuthentication{

    @Autowired
    private UserEntityMapper userDao;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String CLIENT_ID = "";

    private static final String CLIENT_SECRET = "";

    private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    private static final String GITHUB_USER_URL = "https://api.github.com/user";

    @Override
    @Transactional
    public String getUserId(String code) {

        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("client_id", CLIENT_ID);
        requestEntity.add("client_secret", CLIENT_SECRET);
        requestEntity.add("code", code);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GITHUB_ACCESS_TOKEN_URL, requestEntity, String.class);

        String message = responseEntity.getBody().trim();

        String access_token = message.split("&")[0].split("=")[1];

        if(access_token == null || "".equals(access_token)){
            throw new LoginFailureException(message);
        }

        String url = GITHUB_USER_URL + "?access_token=" + access_token;

        responseEntity = restTemplate.getForEntity(url, String.class);

        UserEntity userEntity = null;

        try {

            JSONObject githubUserInfo = new JSONObject(responseEntity.getBody().trim());

            String accountName = githubUserInfo.getString("login");

            if (accountName == null) {
                throw new LoginFailureException(githubUserInfo.toString());
            }

            userEntity = userDao.getUserByAccountName(accountName);

            if (userEntity == null) {
                return insertUser(githubUserInfo);
            } else {
                return userEntity.getAccountName();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String insertUser(JSONObject githubToken) throws JSONException {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(githubToken.getString("email"));
        userEntity.setAccountName(githubToken.getString("login"));
//        userEntity.setUrl(githubToken.getString("html_url"));
        userEntity.setUserType(0);

        userDao.insert(userEntity);


        return userEntity.getAccountName();
    }
}
