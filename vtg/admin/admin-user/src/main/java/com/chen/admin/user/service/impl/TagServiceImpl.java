package com.chen.admin.user.service.impl;

import com.chen.admin.user.service.TagService;
import com.chen.admin.user.base.service.impl.BaseServiceImpl;
import com.chen.admin.user.entity.TagEntity;
import com.chen.admin.user.mapper.TagEntityMapper;
import org.springframework.stereotype.Service;

/**
 * @Author : Chen
 * @Date : Create in 2019/5/1 10:33
 * @Description :
 * @Modify by :
 */

@Service
public class TagServiceImpl extends BaseServiceImpl<TagEntity, String, TagEntityMapper> implements TagService {
}
