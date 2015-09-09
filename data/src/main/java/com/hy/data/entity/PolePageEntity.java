package com.hy.data.entity;

import java.util.List;

/**
 * Created by 24363 on 2015/9/9.
 */
public class PolePageEntity {
    //当前页数
    private int pageNum;
    //每页显示条数
    private int pageSize=10;
    //总记录数
    private int rowCount;
    //结果放在集合里
    private List<PoleEntity> list;
    //总共有多少页
    private int   totalPage;

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

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<PoleEntity> getList() {
        return list;
    }

    public void setList(List<PoleEntity> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
