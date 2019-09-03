package com.chen.admin.user.entity;

import lombok.Data;

import java.util.List;

@Data
public class RoleEntity {

    private Integer id;

    private String role;

    private String roleName;

    private String description;

    /**
     * 权限
     */
    private List<PermissionEntity> permissions;

}