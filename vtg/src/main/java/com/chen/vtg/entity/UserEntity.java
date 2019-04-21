package com.chen.vtg.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEntity {

    private Integer id;

    private String accountName;

    private String name;

    private Integer userType;

    private String mobile;

    private String password;

    private String email;

    private String url;

    private LocalDateTime createTime;


}