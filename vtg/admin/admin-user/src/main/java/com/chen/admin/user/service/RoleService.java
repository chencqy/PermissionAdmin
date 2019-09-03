package com.chen.admin.user.service;

import com.chen.admin.user.base.service.BaseService;
import com.chen.admin.user.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService extends BaseService<RoleEntity, Integer> {

    /**
     * @Author : Chen
     * @Param : [userID]
     * @Return : List<String>
     * @Description :
     * @Create: 2018/9/8 21:29
     */
    List<RoleEntity> getRoleByUserId(int userID);
}
