package com.chen.admin.user.service.impl;

import com.chen.admin.user.service.RoleService;
import com.chen.admin.user.base.service.impl.BaseServiceImpl;
import com.chen.admin.user.entity.RoleEntity;
import com.chen.admin.user.mapper.RoleEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/9 9:49
 * @Description :
 * @Modify by :
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity, Integer, RoleEntityMapper> implements RoleService {

    @Autowired
    RoleEntityMapper roleEntityMapper;

    @Override
    public List<RoleEntity> getRoleByUserId(int userID) {

        return roleEntityMapper.getRoleByUserId(userID);
    }
}
