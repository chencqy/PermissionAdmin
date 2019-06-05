package com.chen.vtg.service;

import com.chen.vtg.base.service.BaseService;
import com.chen.vtg.entity.UserEntity;
import com.chen.vtg.entity.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService<UserEntity, String> {

    /**
      * @Author : Chen
      * @Param : [userEntity]
      * @Return : void
      * @Description :
      * @Create: 2018/8/19 9:58
      */
    void insertIntoUser(UserEntity userEntity);

    /**
     * @param accountName
     * @return UserVo
     */
    UserVo getUserInfo(String accountName);
}
