package com.chen.vtg.entity.vo;

import com.chen.vtg.entity.TagEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2019/5/4 20:39
 * @Description :
 * @Modify by :
 */
@Data
public class ArticleVo {

    private Integer id;

    private Integer uid;

    private String author;

    private String title;

    private String content;

    private String image;

    private Integer status;

    private Integer vote;

    private LocalDateTime createTime;

    private List<TagEntity> tags;

}
