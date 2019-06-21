package com.chen.vtg.mapper;

import com.chen.vtg.base.mapper.BaseMapper;
import com.chen.vtg.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityMapper extends BaseMapper<UserEntity, String> {

    /**
     * @param accountName
     * @return UserEntity
     */
    UserEntity getUserByAccountName(String accountName);

    /**
     *
     * @param userId
     */
    void insertUserRoleDefault(Integer userId);

    /**
     *
     * @param userId
     * @param avatar
     */
    void updateAvatar(@Param("userId") Integer userId, @Param("avatar") String avatar);
}