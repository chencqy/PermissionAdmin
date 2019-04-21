package com.chen.vtg.service;

import com.chen.vtg.base.service.BaseService;
import com.chen.vtg.entity.RoleEntity;
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
