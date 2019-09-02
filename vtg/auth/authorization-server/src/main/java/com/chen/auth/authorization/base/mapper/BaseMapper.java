package com.chen.auth.authorization.base.mapper;

import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/14 18:46
 * @Description : 基础Mapper类
 * @Modify by :
 */
public interface BaseMapper<T, K> {

    int deleteByPrimaryKey(K id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(K id);

    List<T> selectList();

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}