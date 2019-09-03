package com.chen.admin.user.controller;

import com.chen.admin.user.base.controller.BaseController;
import com.chen.admin.user.entity.TagEntity;
import com.chen.admin.user.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Chen
 * @Date : Create in 2019/5/1 10:34
 * @Description :
 * @Modify by :
 */

@Api("文章标签接口")
@RestController
@RequestMapping("/tag")
public class TagController extends BaseController<TagEntity, String, TagService> {
}
