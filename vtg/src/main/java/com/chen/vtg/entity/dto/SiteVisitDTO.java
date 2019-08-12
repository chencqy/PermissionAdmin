package com.chen.vtg.entity.dto;

import com.chen.vtg.entity.vo.VisitVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Chen
 * @Date : Create in 2019/8/11 10:30
 * @Description :
 * @Modify by :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteVisitDTO {
    /**
     * 站点访问统计
     */
    private VisitVo siteVO;

    /**
     * 页面访问统计
     */
    private VisitVo uriVO;
}
