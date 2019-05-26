package com.chen.vtg.controller;

import com.chen.vtg.base.controller.BaseController;
import com.chen.vtg.entity.ArticleEntity;
import com.chen.vtg.entity.vo.ArticleVo;
import com.chen.vtg.entity.vo.PageVo;
import com.chen.vtg.entity.vo.UserVo;
import com.chen.vtg.service.ArticleService;
import com.chen.vtg.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Chen
 * @Date : Create in 2019/4/28 20:44
 * @Description :
 * @Modify by :
 */
@Api("文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<ArticleEntity, Integer, ArticleService> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

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

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param title
     * @param author
     * @param status
     * @return PageInfo<ArticleVo>
     */
    @ApiOperation(value = "搜索文章", notes = "条件查询文章")
    @GetMapping("/search")
    public PageInfo<ArticleVo> searchArticle(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize,
                                             @RequestParam("title")String title, @RequestParam("author")String author,
                                             @RequestParam("status")String status) {
        PageVo page = new PageVo();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setTitle(title);
        articleVo.setAuthor(author);
        articleVo.setStatus(status);
        PageInfo<ArticleVo> articleLists = articleService.getArticleListSelective(page, articleVo);
        return articleLists;
    }

    @ApiOperation(value = "获取文章详情", notes = "根据文章id获取文章详情")
    @GetMapping("/detail")
    public ArticleVo getArticleById(Integer id) {
        ArticleVo article = articleService.getArticleById(id);
        return article;
    }

    @ApiOperation(value = "添加文章", notes = "添加新的文章")
    @PostMapping("/add")
    public int insertArticle(@RequestBody ArticleEntity article) {
        UserVo user = userService.getUserInfo(article.getAuthor());
        article.setUid(user.getId());
        /**
         * 成功返回1
         */
        return articleService.insertSelective(article);
    }

    @PostMapping("/image/upload")
    public Map<String, String> uploadImg(@RequestParam("file") MultipartFile image) {

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("time", String.valueOf(new Date().getTime()));

        String pictureUrl = articleService.saveImage(image);
        resultMap.put("url", pictureUrl);
        return resultMap;
    }

}
