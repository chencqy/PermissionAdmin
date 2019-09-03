package com.chen.admin.user.controller;

import com.chen.admin.user.entity.dto.SiteVisitDTO;
import com.chen.admin.user.entity.dto.VisitReqDTO;
import com.chen.admin.user.entity.vo.VisitVo;
import com.chen.admin.user.service.VisitService;
import com.chen.admin.user.utils.UrlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Chen
 * @Date : Create in 2019/8/11 17:22
 * @Description :
 * @Modify by :
 */

@Api("访问统计接口")
@Slf4j
@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    private VisitVo doVisit(String app, String uri, String ip) {
        String pvKey = visitService.buildPvKey(app);
        String hotKey = visitService.buildHotKey(app);
        String uvKey = visitService.buildUvKey(app, uri);
        String todayVisitKey = visitService.buildUriTagKey(app, uri);

        Long hot = visitService.addHot(hotKey, uri);

        // 获取pv数据
        Long pv = visitService.getPv(pvKey, uri);
        if (pv == null || pv == 0) {
            // 历史没有访问过，则pv + 1, uv +1
            visitService.addPv(pvKey, uri);
            visitService.addUv(uvKey, ip, 1L);
            visitService.tagVisit(todayVisitKey, ip);
            return new VisitVo(1L, 1L, 1L, hot);
        }


        // 判断ip今天是否访问过
        boolean visit = visitService.visitToday(todayVisitKey, ip);

        // 获取uv及排名
        ImmutablePair</**uv*/Long, /**rank*/Long> uv = visitService.getUv(uvKey, ip);

        if (visit) {
            // 今天访问过，则不需要修改pv/uv；可以直接返回所需数据
            return new VisitVo(pv, uv.getLeft(), uv.getRight(), hot);
        }

        // 今天没访问过
        if (uv.left == 0L) {
            // 首次有人访问, pv + 1; uv +1
            visitService.addPv(pvKey, uri);
            visitService.addUv(uvKey, ip, 1L);
            visitService.tagVisit(todayVisitKey, ip);
            return new VisitVo(pv + 1, 1L, 1L, hot);
        } else if (uv.right == 0L) {
            // 这个ip首次访问, pv +1; uv + 1
            visitService.addPv(pvKey, uri);
            visitService.addUv(uvKey, ip, uv.left + 1);
            visitService.tagVisit(todayVisitKey, ip);
            return new VisitVo(pv + 1, uv.left + 1, uv.left + 1, hot);
        } else {
            // 这个ip的今天第一次访问， pv + 1 ; uv 不变
            visitService.addPv(pvKey, uri);
            visitService.tagVisit(todayVisitKey, ip);
            return new VisitVo(pv + 1, uv.left, uv.right, hot);
        }
    }

    /**
     * uri 访问统计
     *
     * @param reqDTO
     * @return
     */
    @ApiOperation(value = "统计访问次数")
    @RequestMapping("/count")
    public SiteVisitDTO visit(VisitReqDTO reqDTO) {
        ImmutablePair<String, String> uri = UrlUtil.formatUri(reqDTO.getUri());

        // 获取站点的访问记录
        VisitVo uriVisit = doVisit(reqDTO.getApp(), uri.getRight(), reqDTO.getIp());
        VisitVo siteVisit;
        if (uri.getLeft().equals(uri.getRight())) {
            siteVisit = new VisitVo(uriVisit);
        } else {
            siteVisit = doVisit(reqDTO.getApp(), uri.getLeft(), reqDTO.getIp());
        }

        return new SiteVisitDTO(siteVisit, uriVisit);
    }
}
