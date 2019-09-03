package com.chen.admin.user.entity.vo;

/**
 * @Author : Chen
 * @Date : Create in 2019/1/14 19:45
 * @Description :
 * @Modify by :
 */
public class PageVo {

    private int pageNum;

    private int pageSize;

    private String search = null;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
