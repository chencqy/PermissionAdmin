package com.chen.vtg.entity;

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

    private Long id;

    private Long uid;

    private String title;

    private String content;

    private String image;

    private Integer status;

    private Integer vote;

    private LocalDateTime createTime;

}
