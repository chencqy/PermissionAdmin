package com.chen.auth.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author : Chen
 * @Date : Create in 2019/9/1 11:44
 * @Description :
 * @Modify by :
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2AuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthorizationApplication.class, args);
    }
}
