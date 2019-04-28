package com.chen.vtg.mapper;

import com.chen.vtg.base.mapper.BaseMapper;
import com.chen.vtg.entity.ArticleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleEntityMapper extends BaseMapper<ArticleEntity, String> {

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
