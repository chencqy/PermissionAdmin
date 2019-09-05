package com.chen.auth.authorization.entity.vo;

import lombok.Data;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/22 20:36
 * @Description :
 * @Modify by :
 */
@Data
public class ButtonVo {

    private Integer id;

    private String resources;

    private String name;

    public ButtonVo(Integer id, String resources, String name) {
        this.id = id;
        this.resources = resources;
        this.name = name;
    }
}
