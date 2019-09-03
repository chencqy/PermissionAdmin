package com.chen.admin.user.entity.vo;

import com.chen.admin.user.entity.RoleEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/22 20:22
 * @Description :
 * @Modify by :
 */
@Data
public class UserVo {

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

    /**
     * 角色
     */
    private List<RoleEntity> roles;

    /**
     * 菜单权限
     */
    private List<MenuVo> menus;

    /**
     * 按钮权限
     */
    private List<ButtonVo> buttons;

    public UserVo(Integer id, String avatar, String accountName, String name, Integer userType, String mobile,
                  String email, String url, LocalDateTime createTime, String info, List<RoleEntity> roles,
                  List<MenuVo> menus, List<ButtonVo> buttons) {
        this.id = id;
        this.avatar = avatar;
        this.accountName = accountName;
        this.name = name;
        this.userType = userType;
        this.mobile = mobile;
        this.email = email;
        this.url = url;
        this.createTime = createTime;
        this.info = info;
        this.roles = roles;
        this.menus = menus;
        this.buttons = buttons;
    }
}
