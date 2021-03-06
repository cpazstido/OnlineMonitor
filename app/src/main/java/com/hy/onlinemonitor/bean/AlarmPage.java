package com.hy.onlinemonitor.bean;

import java.util.ArrayList;
import java.util.List;

public class AlarmPage {
    private int pageNum;
    private int pageSize=10;
    private int rowCount;
    private List<AlarmInformation> list;
    private int totalPage;

    public AlarmPage() {
        list = new ArrayList<>();
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

    public List<AlarmInformation> getList() {
        return list;
    }

    public void setList(List<AlarmInformation> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
