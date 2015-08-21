package com.hy.onlinemonitor.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24363 on 2015/8/21.
 */
public class Page {
    //当前页数
    private int pageNum;
    //每页显示条数
    private int pageSize=10;
    //总记录数
    private int rowCount;
    //结果放在集合里
    private List list;
    //页面上显示多少个序号
    private int pagecode = 5;
    //总共有多少页
    private int   totalPage;

    public Page() {
        list = new ArrayList();
    }

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

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getPagecode() {
        return pagecode;
    }

    public void setPagecode(int pagecode) {
        this.pagecode = pagecode;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}

