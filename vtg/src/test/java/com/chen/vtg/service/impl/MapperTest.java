package com.chen.vtg.service.impl;

import com.chen.vtg.entity.*;
import com.chen.vtg.entity.vo.PageVo;
import com.chen.vtg.mapper.PermissionEntityMapper;
import com.chen.vtg.mapper.RoleEntityMapper;
import com.chen.vtg.mapper.UserEntityMapper;
import com.chen.vtg.service.ArticleService;
import com.chen.vtg.service.TagService;
import com.chen.vtg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MapperTest {


    @Autowired
    UserEntityMapper userDao;

    @Autowired
    RoleEntityMapper roleDao;

    @Autowired
    PermissionEntityMapper permissionDao;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @Test
    public void getRoleByUserId() {

        String accountName = "admin";
        UserEntity userEntity = userDao.getUserByAccountName(accountName);


        List<RoleEntity> roleEntity = roleDao.getRoleByUserId(userEntity.getId());

        Iterator<RoleEntity> iterator =  roleEntity.iterator();
        while (iterator.hasNext()){
            log.info("role : {}", iterator.next().getRole());
        }
    }

    @Test
    public void addPermission() {
        for (int i = 113; i < 117; i++) {
            LocalDateTime time = LocalDateTime.now();
            int parent = 54;
            String resource = "article:";
            String name = "文章";
            String type = "button";
            PermissionEntity permissionEntity = new PermissionEntity();
            permissionEntity.setId(i);
            permissionEntity.setParent(parent);
            permissionEntity.setResources(resource);
            permissionEntity.setName(name);
            permissionEntity.setType(type);
            permissionEntity.setCreateTime(time);
            permissionDao.insert(permissionEntity);
        }
//        Integer i = new Integer(1);
//        PermissionEntity permissionEntity = permissionDao.selectByPrimaryKey(i);
//        LocalDateTime time = LocalDateTime.now();
//        permissionEntity.setCreateTime(time);
//        permissionDao.updateByPrimaryKey(permissionEntity);
    }

    @Test
    public void getUserInfo(){
        String accountName = "admin";
        log.info("user:{}", userService.getUserInfo(accountName));
    }

    @Test
    public void getArticleList(){
        PageVo pageVo = new PageVo();
        pageVo.setPageNum(1);
        pageVo.setPageSize(20);
//        log.info("Article:{}", articleService.selectList(pageVo));
        log.info("Article:{}", articleService.getArticleList(pageVo));
    }

    @Test
    public void addTag(){
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName("C++");
        int i = tagService.insert(tagEntity);
        System.out.println(i);
    }

    @Test
    public void addArticle(){
        ArticleEntity article = new ArticleEntity();
        article.setUid(1);
        article.setContent("yes");
        article.setImage("www.baidu.com");
        article.setStatus("published");
        article.setVote(1);
        article.setCreateTime(LocalDateTime.now());
        for (int i = 0; i < 16; i++) {
            article.setId(i + 2);
            article.setTitle("article" + i);
            articleService.updateByPrimaryKeySelective(article);
        }
    }
}