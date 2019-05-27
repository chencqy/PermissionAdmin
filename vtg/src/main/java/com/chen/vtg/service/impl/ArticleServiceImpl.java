package com.chen.vtg.service.impl;

import com.chen.vtg.base.service.impl.BaseServiceImpl;
import com.chen.vtg.entity.ArticleEntity;
import com.chen.vtg.entity.vo.ArticleVo;
import com.chen.vtg.entity.vo.PageVo;
import com.chen.vtg.mapper.ArticleEntityMapper;
import com.chen.vtg.service.ArticleService;
import com.chen.vtg.utils.ImageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/4/28 20:40
 * @Description :
 * @Modify by :
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleEntity, Integer, ArticleEntityMapper> implements ArticleService {

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

    @Override
    public PageInfo<ArticleVo> getArticleList(PageVo page) {
        return PageHelper.startPage(page.getPageNum(), page.getPageSize()).
                doSelectPageInfo(() -> articleEntityMapper.getArticleList());
    }

    @Override
    public ArticleVo getArticleById(Integer id) {
        return articleEntityMapper.getArticleById(id);
    }

    @Override
    public PageInfo<ArticleVo> getArticleListSelective(PageVo page, ArticleVo articleVo) {
        return PageHelper.startPage(page.getPageNum(), page.getPageSize()).
                doSelectPageInfo(() -> articleEntityMapper.getArticleListSelective(articleVo));
    }

    @Override
    public String saveImage(MultipartFile image) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime time = LocalDateTime.now();
        String timePath = time.format(formatter);
        String path = "E:\\Program\\image\\" + timePath;

        String imageUrl = null;
        try {
            if (image != null) {
                String fileName = ImageUtil.saveImg(image, path);
                imageUrl = "http://localhost/image/" + timePath + "/" + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageUrl;
    }
}
