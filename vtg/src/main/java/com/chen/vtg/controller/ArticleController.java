package com.chen.vtg.controller;

import com.chen.vtg.base.controller.BaseController;
import com.chen.vtg.entity.ArticleEntity;
import com.chen.vtg.entity.vo.ArticleVo;
import com.chen.vtg.entity.vo.PageVo;
import com.chen.vtg.service.ArticleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Chen
 * @Date : Create in 2019/4/28 20:44
 * @Description :
 * @Modify by :
 */
@Api("文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<ArticleEntity, String, ArticleService> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleService articleService;

    /**
     *
     * @param page
     * @return articleLists
     */
    @ApiOperation(value = "查询文章", notes = "查询所有文章")
    @GetMapping("/list")
    public PageInfo<ArticleVo> selectList(PageVo page) {
        PageInfo<ArticleVo> articleLists = articleService.getArticleList(page);
        return articleLists;
    }

}
