package com.chen.admin.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author : Chen
 * @Date : Create in 2019/9/2 20:59
 * @Description :
 * @Modify by :
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.chen.admin.user.mapper")
public class AdminUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminUserApplication.class, args);
    }
}
