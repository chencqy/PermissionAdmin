package com.chen.admin.user.mapper;

import com.chen.admin.user.base.mapper.BaseMapper;
import com.chen.admin.user.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleEntityMapper extends BaseMapper<RoleEntity, String> {

    /**
     *
     * @param userId
     * @return RoleEntity
     */
    List<RoleEntity> getRoleByUserId(int userId);

}