package com.chen.vtg.base.controller;

import com.chen.vtg.base.service.BaseService;
import com.chen.vtg.entity.vo.PageVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/15 20:08
 * @Description :
 * @Modify by :
 */
@Slf4j
public class BaseController<T, K, S extends BaseService<T, K>> {

    @Autowired
    protected S baseService;

    @GetMapping("/{id}")
    public T get(@PathVariable("id") K id) {
        log.info("get ID : {}", id);
        return baseService.selectByPrimaryKey(id);
    }

    @GetMapping("/page")
    public PageInfo<T> paging(PageVo page) {
        log.info("page :{} size : {}", page.getPageNum(), page.getPageSize());
        return baseService.selectList(page);
    }

    @PostMapping("/update")
    public Integer update(@RequestBody T entity) {
        log.info("update :{}", entity);
        return baseService.updateByPrimaryKey(entity);
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable("id") K id) {
        log.info("delete ID :{}", id);
        return baseService.deleteByPrimaryKey(id);
    }

    @PutMapping("/save")
    public Integer save(@RequestBody T entity) {
        return baseService.insertSelective(entity);
    }
}
