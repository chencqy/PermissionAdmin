package com.chen.admin.user.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/22 20:36
 * @Description :
 * @Modify by :
 */
@Data
public class MenuVo {

    private Integer id;

    private Integer parent;

    private String icon;

    private String resources;

    private String name;

    private List<MenuVo> children;

    public MenuVo(Integer id, Integer parent, String icon, String resources, String name) {
        this.id = id;
        this.parent = parent;
        this.icon = icon;
        this.resources = resources;
        this.name = name;
    }
}
