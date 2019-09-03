package com.chen.admin.user.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Chen
 * @Date : Create in 2019/4/28 19:53
 * @Description :
 * @Modify by :
 */

@Data
public class ArticleEntity {

    private Integer id;

    private Integer uid;

    private String title;

    private String author;

    private String content;

    private String image;

    private String status;

    private Integer vote;

    private LocalDateTime createTime;

}
