package com.chen.auth.authorization.mapper;

import com.chen.auth.authorization.base.mapper.BaseMapper;
import com.chen.auth.authorization.entity.RoleEntity;
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