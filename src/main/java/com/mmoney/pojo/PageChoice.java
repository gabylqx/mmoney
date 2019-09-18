package com.mmoney.pojo;

public class PageChoice {
    private Integer pageNow;
    private Integer beginRow;
    private Integer pageSize;
    private Integer pageCnt = 1;
    //分页条件
    private Integer id;

    public PageChoice() {
    }

    @Override
    public String toString() {
        return "PageChoice{" +
                "pageNow=" + pageNow +
                ", beginRow=" + beginRow +
                ", pageSize=" + pageSize +
                ", pageCnt=" + pageCnt +
                ", id=" + id +
                '}';
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(Integer beginRow) {
        this.beginRow = beginRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(Integer pageCnt) {
        this.pageCnt = pageCnt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
