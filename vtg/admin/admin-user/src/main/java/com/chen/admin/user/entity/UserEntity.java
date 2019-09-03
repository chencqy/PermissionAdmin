package com.chen.admin.user.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEntity {

    private Integer id;

    private String avatar;

    private String accountName;

    private String name;

    private Integer userType;

    private String mobile;

    private String password;

    private String email;

    private String url;

    private LocalDateTime createTime;

    private String info;


}