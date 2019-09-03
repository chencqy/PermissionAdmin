package com.chen.admin.user.entity.dto;

import lombok.Data;

/**
 * @Author : Chen
 * @Date : Create in 2019/8/11 9:44
 * @Description :
 * @Modify by :
 */
@Data
public class VisitReqDTO {
    /**
     * 应用区分
     */
    private String app;

    /**
     * 访问者ip
     */
    private String ip;

    /**
     * 访问的URI
     */
    private String uri;
}
