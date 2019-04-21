package com.chen.vtg.mapper;

import com.chen.vtg.base.mapper.BaseMapper;
import com.chen.vtg.entity.RoleEntity;
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