package com.hy.data.entity;

import java.util.List;

/**
 * Created by 24363 on 2015/8/31.
 */
public class CompanyEntity {
    private int sn;
    private String companyName;
    private List<LineEntity> lineList;

    public CompanyEntity() {
    }

    public List<LineEntity> getLineList() {
        return lineList;
    }

    public void setLineList(List<LineEntity> lineList) {
        this.lineList = lineList;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
