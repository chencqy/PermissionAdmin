package com.chen.vtg.mapper;

import com.chen.vtg.base.mapper.BaseMapper;
import com.chen.vtg.entity.ArticleEntity;
import com.chen.vtg.entity.vo.ArticleVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleEntityMapper extends BaseMapper<ArticleEntity, Integer> {

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
     * @return List<ArticleVo>
     */
    List<ArticleVo> getArticleList();

    /**
     *
     * @param id
     * @return
     */
    ArticleVo getArticleById(Integer id);

    /**
     * @param articleVo
     * @return List<ArticleVo>
     */
    List<ArticleVo> getArticleListSelective(ArticleVo articleVo);
}
