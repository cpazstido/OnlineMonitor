package com.hy.onlinemonitor.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/11.
 */
public class EquipmentPage {
    private int pageNum;                        //当前页数
    private int pageSize = 10;                  //每页显示的数量
    private int rowCount;                       //总数目
    private List<Equipment> list;               //数据
    private int totalPage;                      //总页数

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

    public List<Equipment> getList() {
        return list;
    }

    public void setList(List<Equipment> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
