package com.chen.admin.user.base.service.impl;

import com.chen.admin.user.base.mapper.BaseMapper;
import com.chen.admin.user.base.service.BaseService;
import com.chen.admin.user.entity.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/14 19:54
 * @Description :
 * @Modify by :
 */
@Slf4j
public class BaseServiceImpl<T, K, M extends BaseMapper> implements BaseService<T, K> {

    @Autowired
    protected M baseMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int deleteByPrimaryKey(K id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(K id) {
        return (T) baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<T> selectList(PageVo page) {

        log.info("selectList: PageNum: {}  PageSize:{}", page.getPageNum(), page.getPageSize());
        return PageHelper.startPage(page.getPageNum(), page.getPageSize()).doSelectPageInfo(() -> baseMapper.selectList());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }
}
