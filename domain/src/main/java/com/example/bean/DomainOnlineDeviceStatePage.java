package com.example.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/10/22.
 */
public class DomainOnlineDeviceStatePage {
    private int pageNum;
    private int pageSize=10;
    private int rowCount;
    private List<DomainOnlineDeviceState> list;
    private int totalPage;

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

    public List<DomainOnlineDeviceState> getList() {
        return list;
    }

    public void setList(List<DomainOnlineDeviceState> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
