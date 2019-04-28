package com.chen.vtg.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/4/28 19:53
 * @Description :
 * @Modify by :
 */

@Data
public class ArticleEntity {

    private Long id;

    private Long uid;

    private String title;

    private String content;

    private String image;

    private Integer status;

    private Integer vote;

    private LocalDateTime createTime;

    /**
     * 文章所含标签
     */
    private List<TagEntity> tags;



}
