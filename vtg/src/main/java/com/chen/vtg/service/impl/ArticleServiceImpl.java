package com.chen.vtg.service.impl;

import com.chen.vtg.base.mapper.BaseMapper;
import com.chen.vtg.base.service.impl.BaseServiceImpl;
import com.chen.vtg.entity.ArticleEntity;
import com.chen.vtg.mapper.ArticleEntityMapper;
import com.chen.vtg.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/4/28 20:40
 * @Description :
 * @Modify by :
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleEntity, String, BaseMapper> implements ArticleService {

    @Autowired
    ArticleEntityMapper articleEntityMapper;

    @Override
    public List<ArticleEntity> getArticleByTitle(String title) {
        return articleEntityMapper.getArticleByTitle(title);
    }

    @Override
    public List<ArticleEntity> getArticleByUid(int uid) {
        return articleEntityMapper.getArticleByUid(uid);
    }
}
