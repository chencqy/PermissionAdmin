package com.chen.admin.user.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : Chen
 * @Date : Create in 2019/8/11 9:39
 * @Description :
 * @Modify by :
 */
@Data
@AllArgsConstructor
public class VisitVo implements Serializable {
    /**
     * pv，与传统的有点区别，这里表示这个url的总访问次数；每个ip，一天次数只+1
     */
    private Long pv;

    /**
     * uv 页面总的ip访问数
     */
    private Long uv;

    /**
     * 当前ip，第一次访问本url的排名
     */
    private Long rank;

    /**
     * 热度，每次访问计数都+1
     */
    private Long hot;

    public VisitVo() {
    }

    public VisitVo(VisitVo visitVO) {
        this.pv = visitVO.pv;
        this.uv = visitVO.uv;
        this.rank = visitVO.rank;
        this.hot = visitVO.hot;
    }
}
