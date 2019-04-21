package com.chen.vtg.service.impl;

import com.chen.vtg.base.mapper.BaseMapper;
import com.chen.vtg.base.service.impl.BaseServiceImpl;
import com.chen.vtg.entity.RoleEntity;
import com.chen.vtg.entity.UserEntity;
import com.chen.vtg.entity.vo.ButtonVo;
import com.chen.vtg.entity.vo.MenuVo;
import com.chen.vtg.entity.vo.UserVo;
import com.chen.vtg.mapper.RoleEntityMapper;
import com.chen.vtg.mapper.UserEntityMapper;
import com.chen.vtg.service.UserService;
import com.chen.vtg.utils.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/19 9:56
 * @Description :
 * @Modify by :
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, String, BaseMapper> implements UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Override
    public int insertIntoUser(UserEntity userEntity) {
        String password = userEntity.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userEntity.setPassword(password);
        return userEntityMapper.insert(userEntity);
    }

    @Override
    public UserVo getUserInfo(String accountName) {
        UserEntity userEntity = userEntityMapper.getUserByAccountName(accountName);
        List<RoleEntity> roles = roleEntityMapper.getRoleByUserId(userEntity.getId());
        List<MenuVo> menus = new ArrayList<>();
        List<ButtonVo> buttons = new ArrayList<>();

        roles.forEach(role -> role.getPermissions().forEach(permission -> {
            if (permission.getType().equals("menu")) {
                menus.add(new MenuVo(permission.getId(), permission.getParent(), permission.getIcon(),
                                     permission.getResources(), permission.getName()));
            }
            if (permission.getType().equals("button")) {
                buttons.add(new ButtonVo(permission.getId(), permission.getResources(), permission.getName()));
            }
        }));

        UserVo userVo = new UserVo(userEntity.getId(), userEntity.getAccountName(), userEntity.getName(),
                                   userEntity.getUserType(), userEntity.getMobile(),userEntity.getEmail(),
                                   userEntity.getUrl(), userEntity.getCreateTime(),
                                   roles, TreeNode.findRoot(menus), buttons);
        return userVo;
    }


}
