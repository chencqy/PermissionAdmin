package com.chen.vtg.mapper;

import com.chen.vtg.base.service.BaseService;
import com.chen.vtg.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityMapper extends BaseService<UserEntity, String> {

    /**
     * @param accountName
     * @return UserEntity
     */
    UserEntity getUserByAccountName(String accountName);
}