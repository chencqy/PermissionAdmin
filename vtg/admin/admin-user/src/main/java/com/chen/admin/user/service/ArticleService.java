package com.chen.admin.user.service;

import com.chen.admin.user.base.service.BaseService;
import com.chen.admin.user.entity.ArticleEntity;
import com.chen.admin.user.entity.vo.ArticleVo;
import com.chen.admin.user.entity.vo.PageVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService extends BaseService<ArticleEntity, Integer> {

    /**
     * @param title
     * @return List<ArticleEntity>
     */
    List<ArticleEntity> getArticleByTitle(String title);

    /**
     * @param uid
     * @return List<ArticleEntity>
     */
    List<ArticleEntity> getArticleByUid(int uid);

    /**
     *
     * @return PageInfo<ArticleEntity>
     */
    PageInfo<ArticleVo> getArticleList(PageVo page);

    /**
     *
     * @param id
     * @return ArticleVo
     */
    ArticleVo getArticleById(Integer id);

    /**
     *
     * @param page
     * @return PageInfo<ArticleVo>
     */
    PageInfo<ArticleVo> getArticleListSelective(PageVo page, ArticleVo articleVo);

}
