package com.hy.data.entity;

import java.util.List;

/**
 * Created by 24363 on 2015/8/31.
 */
public class CompanyEntity {
    private int sn;
    private String companyName;
    private List<LineEntity> lineList;
    private String companyAddress;
    private String parentCompanyName;

    public CompanyEntity() {
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getParentCompanyName() {
        return parentCompanyName;
    }

    public void setParentCompanyName(String parentCompanyName) {
        this.parentCompanyName = parentCompanyName;
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
