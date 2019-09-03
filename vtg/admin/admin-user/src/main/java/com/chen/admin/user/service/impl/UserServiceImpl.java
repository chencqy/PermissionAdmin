package com.chen.admin.user.service.impl;

import com.chen.admin.user.service.ImageService;
import com.chen.admin.user.service.UserService;
import com.chen.admin.user.base.service.impl.BaseServiceImpl;
import com.chen.admin.user.entity.RoleEntity;
import com.chen.admin.user.entity.UserEntity;
import com.chen.admin.user.entity.vo.ButtonVo;
import com.chen.admin.user.entity.vo.MenuVo;
import com.chen.admin.user.entity.vo.PageVo;
import com.chen.admin.user.entity.vo.UserVo;
import com.chen.admin.user.mapper.RoleEntityMapper;
import com.chen.admin.user.mapper.UserEntityMapper;
import com.chen.admin.user.utils.TreeNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Integer, UserEntityMapper> implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Autowired
    private ImageService imageService;

    @Override
    public void insertIntoUser(UserEntity userEntity) {
        String password = userEntity.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userEntity.setPassword(password);
        userEntityMapper.insertSelective(userEntity);
        userEntity = userEntityMapper.getUserByAccountName(userEntity.getAccountName());
        userEntityMapper.insertUserRoleDefault(userEntity.getId());
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

        UserVo userVo = new UserVo(userEntity.getId(), userEntity.getAvatar(), userEntity.getAccountName(),
                                   userEntity.getName(), userEntity.getUserType(), userEntity.getMobile(),
                                   userEntity.getEmail(), userEntity.getUrl(), userEntity.getCreateTime(),
                                   userEntity.getInfo(), roles, TreeNode.findRoot(menus), buttons);
        return userVo;
    }

    @Override
    public void updateUserAvatar(int userId, String avatar, String OldAvatar) {
//        if (imageService.deleteImg(OldAvatar)) {
//            logger.info("image: {} delete successfully", OldAvatar);
//        } else {
//            logger.info("image: {} delete failed", OldAvatar);
//        }
        imageService.deleteImgWithAliyunOSS(OldAvatar);
        userEntityMapper.updateAvatar(userId, avatar);
    }

    @Override
    public void updateUserType(Integer userId, Integer userType) {
        logger.info("update user type: {}", userType);
        userEntityMapper.updateUserType(userId, userType);
    }

    @Override
    public PageInfo<UserEntity> getUserListSelective(PageVo page, UserEntity userEntity) {
        return PageHelper.startPage(page.getPageNum(), page.getPageSize()).
                doSelectPageInfo(() -> userEntityMapper.getUserListSelective(userEntity));
    }


}
