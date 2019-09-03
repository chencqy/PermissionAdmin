package com.chen.admin.user.mapper;

import com.chen.admin.user.base.mapper.BaseMapper;
import com.chen.admin.user.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityMapper extends BaseMapper<UserEntity, Integer> {

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

    /**
     *
     * @param userId
     * @param userType
     */
    void updateUserType(@Param("userId") Integer userId, @Param("userType") Integer userType);

    /**
     *
     * @param userEntity
     * @return List<UserEntity>
     */
    List<UserEntity> getUserListSelective(UserEntity userEntity);
}