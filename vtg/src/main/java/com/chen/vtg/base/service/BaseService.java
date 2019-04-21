package com.chen.vtg.base.service;

import com.chen.vtg.entity.vo.PageVo;
import com.github.pagehelper.PageInfo;

public interface BaseService<T, K> {

    int deleteByPrimaryKey(K id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(K id);

    PageInfo<T> selectList(PageVo page);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
