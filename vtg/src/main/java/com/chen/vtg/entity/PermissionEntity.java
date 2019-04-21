package com.chen.vtg.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PermissionEntity {
    private Integer id;

    private Integer parent;

    private String resources;

    private String name;

    private String icon;

    private String type;

    private String description;

    private LocalDateTime createTime;

}