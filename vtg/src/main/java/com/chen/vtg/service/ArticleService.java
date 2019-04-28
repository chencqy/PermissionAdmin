package com.chen.vtg.service;

import com.chen.vtg.base.service.BaseService;
import com.chen.vtg.entity.ArticleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService extends BaseService<ArticleEntity, String> {

    /**
     * @param title
     * @return List<ArticleEntity>
     */
    List<ArticleEntity> getArticleByTitle(String title);

    /**
     * @param
     * @return List<ArticleEntity>
     */
    List<ArticleEntity> getArticleList();

    /**
     * @param uid
     * @return List<ArticleEntity>
     */
    List<ArticleEntity> getArticleByUid(int uid);

}
