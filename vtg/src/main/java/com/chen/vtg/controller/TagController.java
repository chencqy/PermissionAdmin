package com.chen.vtg.controller;

import com.chen.vtg.base.controller.BaseController;
import com.chen.vtg.entity.TagEntity;
import com.chen.vtg.service.TagService;
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
